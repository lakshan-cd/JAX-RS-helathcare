package com.example.resource;
import com.example.DTO.AppoinmentDTO;
import com.example.DTO.MedicalRecordDTO;
import com.example.dao.AppointmentDAO;
import com.example.dao.MedicalRecordDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/medicalrecord")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {


    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @POST
    public Response createMedicalRecord(MedicalRecordDTO request) {
        Boolean result = medicalRecordDAO.createMedicalRecord(request);
        if (result) {
            return Response.ok("Medical Record created successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create Medical Record.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") int id , MedicalRecordDTO request){
        Boolean result = medicalRecordDAO.updateMedicalRecord(id,request);
        if (result) {
            return Response.ok("Medical Record updated successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update Medical Record.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id){
        Boolean result = medicalRecordDAO.deleteMedicalRecord(id);
        if (result) {
            return Response.ok("Medical Record deleted successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete Medical Record.")
                    .build();
        }
    }

    @GET
    public Response getAllMedicalRedords(){
        List<Map<String, Object>> result = medicalRecordDAO.getAllMedicalRecordsWithPatients();
        if (result != null) {
            return Response.ok(result)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error Occurred while fetching the data.")
                    .build();
        }
    }
    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") int id){
        Map<String, Object> result = medicalRecordDAO.getMedicalRecordByIdWithPatient(id);
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
