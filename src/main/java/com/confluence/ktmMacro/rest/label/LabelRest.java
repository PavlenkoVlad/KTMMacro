package com.confluence.ktmMacro.rest.label;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.confluence.ktmMacro.models.Label;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Path("/label")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Scanned
public class LabelRest {

    @ComponentImport
    private ActiveObjects ao;

    @Inject
    public LabelRest(ActiveObjects ao) {
        this.ao = ao;
    }

    @GET
    @AnonymousAllowed
    public Response getAllLabels() {

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                List<LabelRestModel> result = convertToRestModelsList(newArrayList(ao.find(Label.class)));
                return Response.ok(result.toArray()).build();
            }
        });
    }

    @GET
    @AnonymousAllowed
    @Path("{id}")
    public Response getLabel(@PathParam("id") final String id) {
        int modelID = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                return Response.ok(convertToRestModel(ao.get(Label.class, modelID))).build();
            }
        });
    }

    @PUT
    @Path("{id}")
    public Response updateLabel(@PathParam("id") final String id, final LabelRestModel labelModel) {
        int modelID = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                Label updatedLabel = ao.get(Label.class, labelModel.getId());
                updatedLabel.setName(labelModel.getName());
                updatedLabel.save();
                return Response.ok(convertToRestModel(updatedLabel)).build();
            }
        });
    }

    @POST
    public Response createLabel(final LabelRestModel labelModel) {
        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                Label newLabel = ao.create(Label.class);
                newLabel.setName(labelModel.getName());
                newLabel.save();
                return Response.ok(convertToRestModel(newLabel)).build();
            }
        });
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final String id) {
        int labelId = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                Label label = ao.get(Label.class, labelId);
                ao.delete(label);
                return Response.ok().build();
            }
        });
    }

    private LabelRestModel convertToRestModel(Label label) {
        return new LabelRestModel(label.getID(), label.getName());
    }

    private List<LabelRestModel> convertToRestModelsList(List<Label> labels) {
        List<LabelRestModel> list = new ArrayList<>();
        for(Label label : labels)
            list.add(convertToRestModel(label));
        return list;
    }

}
