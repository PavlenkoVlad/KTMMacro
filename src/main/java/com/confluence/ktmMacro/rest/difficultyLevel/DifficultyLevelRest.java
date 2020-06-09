package com.confluence.ktmMacro.rest.difficultyLevel;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.confluence.ktmMacro.models.DifficultyLevel;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Path("/difficultyLevel")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Scanned
public class DifficultyLevelRest {

    @ComponentImport
    private final ActiveObjects ao;

    @Inject
    public DifficultyLevelRest(ActiveObjects ao) {
        this.ao = ao;
    }

    @GET
    @AnonymousAllowed
    public Response getAllDifficultyLevels() {

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                List<DifficultyLevelRestModel> result = convertToRestModelsList(newArrayList(ao.find(DifficultyLevel.class)));
                return Response.ok(result.toArray()).build();
            }
        });
    }

    @GET
    @AnonymousAllowed
    @Path("{id}")
    public Response getDifficultyLevel(@PathParam("id") final String id) {
        int dlId = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                DifficultyLevel dl = ao.get(DifficultyLevel.class, dlId);
                return Response.ok(convertToRestModel(dl)).build();
            }
        });
    }

    @PUT
    @Path("{id}")
    public Response updateDifficultyLevel(@PathParam("id") final String id, final DifficultyLevelRestModel dlModel) {
        int dlId = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                DifficultyLevel updatedDl = ao.get(DifficultyLevel.class, dlId);
                updatedDl.setName(dlModel.getName());
                updatedDl.save();
                return Response.ok(convertToRestModel(updatedDl)).build();
            }
        });
    }

    @POST
    public Response createDifficultyLevel(final DifficultyLevelRestModel dlModel) {

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                DifficultyLevel newDl = ao.create(DifficultyLevel.class);
                newDl.setName(dlModel.getName());
                newDl.save();
                return Response.ok(convertToRestModel(newDl)).build();
            }
        });
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final String id) {
        int dlId = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                DifficultyLevel dl = ao.get(DifficultyLevel.class, dlId);
                ao.delete(dl);
                return Response.ok().build();
            }
        });
    }

    private DifficultyLevelRestModel convertToRestModel(DifficultyLevel dl) {
        return new DifficultyLevelRestModel(dl.getID(), dl.getName());
    }

    private List<DifficultyLevelRestModel> convertToRestModelsList(List<DifficultyLevel> dlList) {
        List<DifficultyLevelRestModel> list = new ArrayList<>();
        for(DifficultyLevel dl : dlList)
            list.add(convertToRestModel(dl));
        return list;
    }

}
