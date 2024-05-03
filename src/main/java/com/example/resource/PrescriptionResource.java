package com.example.resource;

import com.example.DTO.DoctorDTO;
import com.example.DTO.PrescriptionDTO;
import com.example.dao.PrescriptionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

}
