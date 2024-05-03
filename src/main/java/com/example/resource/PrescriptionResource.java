package com.example.resource;

import com.example.DTO.DoctorDTO;
import com.example.DTO.PrescriptionDTO;
import com.example.dao.PrescriptionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/prescription")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @POST
    public Response createPrescription(PrescriptionDTO request) {
        Boolean result = prescriptionDAO.createPrescription(request);
        System.out.println(result);
        if (result) {
            return Response.ok("Prescription created successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create prescription.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") int id , PrescriptionDTO request){
        Boolean result = prescriptionDAO.updatePrescription(id,request);
        if (result) {
            return Response.ok("Prescription updated successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update Prescription.")
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id){
        Boolean result = prescriptionDAO.deletePrescription(id);
        if (result) {
            return Response.ok("Prescription deleted successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete Prescription.")
                    .build();
        }
    }

    @GET
    public Response getAllPrescriptionData(){
        List<Map<String, Object>> result = prescriptionDAO.getAllPrescriptionsWithPatientsAndDoctors();
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
    public Response getDoctorDataById(@PathParam("id") int id){
        Map<String, Object> result = prescriptionDAO.getPrescriptionByIdWithPatientAndDoctor(id);
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
