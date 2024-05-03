package com.example.resource;


import com.example.DTO.AppoinmentDTO;
import com.example.DTO.PrescriptionDTO;
import com.example.dao.AppointmentDAO;
import com.example.dao.PrescriptionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/appointment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppoinmentResource {


    private AppointmentDAO appoinmentDAO = new AppointmentDAO();

    @POST
    public Response createAppointment(AppoinmentDTO request) {
        Boolean result = appoinmentDAO.createAppointment(request);
        System.out.println(result);
        if (result) {
            return Response.ok("Appointment created successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create Appointment.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAppointment(@PathParam("id") int id , AppoinmentDTO request){
        Boolean result = appoinmentDAO.updateAppointment(id,request);
        if (result) {
            return Response.ok("Appoinment updated successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update Appoinment.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id){
        Boolean result = appoinmentDAO.deleteAppointment(id);
        if (result) {
            return Response.ok("Appointment deleted successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete Appointment.")
                    .build();
        }
    }
}
