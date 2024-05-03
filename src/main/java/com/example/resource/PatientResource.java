package com.example.resource;

import com.example.DTO.MedicalRecordDTO;
import com.example.DTO.PatientDTO;
import com.example.dao.PatientDAO;
import com.example.model.PatientEntity;
import com.example.model.PersonEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Path("/patient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    private PatientDAO patientDAO = new PatientDAO();

    @POST
    public Response createPatient(PatientDTO request){
        Boolean result = patientDAO.createPatient(request);
        if (result) {
            return Response.ok("Patient created successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create patient.")
                    .build();
        }
    }
    @GET
    public Response getAllPatients() {
        List<PatientEntity> result = patientDAO.getAllPatients();
        if (!result.isEmpty()) {
            return Response.ok(result)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to get all patients.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePatient(@PathParam("id") int id , PatientDTO request){
        Boolean result = patientDAO.updatePateint(id,request);
        if (result) {
            return Response.ok("patient updated successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update patient.")
                    .build();
        }
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        PatientEntity result = patientDAO.getPatientById(id);
        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to retrieve patient.")
                    .build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id){
        Boolean result = patientDAO.deletePatient(id);
        if (result) {
            return Response.ok("patient deleted successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete patient.")
                    .build();
        }
    }
}
