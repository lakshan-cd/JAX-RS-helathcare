package com.example.resource;




import com.example.DTO.DoctorDTO;
import com.example.dao.DoctorDAO;
import com.example.dao.PersonDAO;
import com.example.model.DoctorEntity;
import com.example.model.PatientEntity;
import com.example.model.PersonEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;


@Path("/doctor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {
    private final DoctorDAO doctorDAO = new DoctorDAO();

    @POST
    public Response createDoctor(DoctorDTO request ) {
        Boolean result = doctorDAO.createDoctor(request);
        System.out.println(result);
        if (result) {
            return Response.ok("Doctor created successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create doctor.")
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    public Response updateDoctor(@PathParam("id") int id , DoctorDTO request){
        Boolean result = doctorDAO.updateDoctor(id,request);
        if (result) {
            return Response.ok("Doctor updated successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update person.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id){
        Boolean result = doctorDAO.deleteDoctor(id);
        if (result) {
            return Response.ok("doctor deleted successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete person.")
                    .build();
        }
    }

    @GET
    public Response getAllDoctorData(){
        List<Map<String, Object>> result = doctorDAO.getAllDoctorsWithPatients();
        if (result != null) {
            return Response.ok(result)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete person.")
                    .build();
        }
    }
    @GET
    @Path("/{id}")
    public Response getDoctorDataById(@PathParam("id") int id){
        Map<String, Object> result = doctorDAO.getDoctorByIdWithPatients(id);
        if (result != null) {
            return Response.ok(result)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete person.")
                    .build();
        }
    }
}
