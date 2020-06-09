package com.confluence.ktmMacro.rest.scope;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.confluence.ktmMacro.models.Scope;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Path("/scope")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Scanned
public class ScopeRest {

    @ComponentImport
    private ActiveObjects ao;

    @Inject
    public ScopeRest(ActiveObjects ao) {
        this.ao = ao;
    }

    @GET
    @AnonymousAllowed
    public Response getAllScopes() {

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                List<ScopeRestModel> result = convertToRestModelsList(newArrayList(ao.find(Scope.class)));
                return Response.ok(result.toArray()).build();
            }
        });
    }

    @GET
    @AnonymousAllowed
    @Path("{id}")
    public Response getScope(@PathParam("id") final String id) {
        int modelID = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                return Response.ok(convertToRestModel(ao.get(Scope.class, modelID))).build();
            }
        });
    }

    @PUT
    @Path("{id}")
    public Response updateScope(@PathParam("id") final String id, final ScopeRestModel scopeModel) {
        int modelID = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                Scope updatedScope = ao.get(Scope.class, modelID);
                updatedScope.setName(scopeModel.getName());
                updatedScope.save();
                return Response.ok(convertToRestModel(updatedScope)).build();
            }
        });
    }

    @POST
    public Response createScope(final ScopeRestModel scopeModel) {

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                Scope newScope = ao.create(Scope.class);
                newScope.setName(scopeModel.getName());
                newScope.save();
                return Response.ok(convertToRestModel(newScope)).build();
            }
        });
    }

    @DELETE
    @Path("{id}")
    public Response deleteScope(@PathParam("id") final String id) {
        int scopeId = Integer.parseInt(id);

        return ao.executeInTransaction(new TransactionCallback<Response>() {

            @Override
            public Response doInTransaction() {
                Scope scope = ao.get(Scope.class, scopeId);
                ao.delete(scope);
                return Response.ok().build();
            }
        });
    }

    private ScopeRestModel convertToRestModel(Scope scope) {
        return new ScopeRestModel(scope.getID(), scope.getName());
    }

    private List<ScopeRestModel> convertToRestModelsList(List<Scope> scopes) {
        List<ScopeRestModel> list = new ArrayList<>();
        for(Scope scope : scopes)
            list.add(convertToRestModel(scope));
        return list;
    }

}