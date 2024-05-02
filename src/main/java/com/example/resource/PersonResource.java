package com.example.resource;

import com.example.dao.PersonDAO;
import com.example.model.PersonEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
   private PersonDAO personDAO = new PersonDAO();

    @GET
    public Response getAllPersons() {
        List<PersonEntity> patients = personDAO.getAllPersons();
        return Response.ok(patients).build();
    }
    @POST
    public Response createPerson(PersonEntity request){
        Boolean result = personDAO.createPerson(request);
        if (result) {
            return Response.ok("Person created successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create person.")
                    .build();
        }
    }
    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id , PersonEntity request){
        Boolean result = personDAO.updatePerson(id,request);
        if (result) {
            return Response.ok("Person updated successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update person.")
                    .build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id){
        Boolean result = personDAO.deletePerson(id);
        if (result) {
            return Response.ok("Person deleted successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete person.")
                    .build();
        }
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id){
        PersonEntity result = personDAO.getPersonById(id);
        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to retrieve person.")
                    .build();
        }
    }
}
