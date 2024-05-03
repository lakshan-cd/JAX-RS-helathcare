package com.example.resource;

import com.example.DTO.BillingInvoiceDTO;
import com.example.DTO.MedicalRecordDTO;
import com.example.dao.BillingInvoiceDAO;
import com.example.dao.MedicalRecordDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/invoice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingInvoiceResource {

    private BillingInvoiceDAO billingInvoiceDAO = new BillingInvoiceDAO();

    @POST
    public Response createBillingInvoice(BillingInvoiceDTO request) {
        Boolean result = billingInvoiceDAO.createInvoice(request);
        System.out.println(result);
        if (result) {
            return Response.ok("Invoice created successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create Invoice.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBillingInvoice(@PathParam("id") int id , BillingInvoiceDTO request){
        Boolean result = billingInvoiceDAO.updateInvoice(id,request);
        if (result) {
            return Response.ok("Invoice updated successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update Invoice.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteInvoice(@PathParam("id") int id){
        Boolean result = billingInvoiceDAO.deleteInvoice(id);
        if (result) {
            return Response.ok("Invoice deleted successfully.").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete Invoice.")
                    .build();
        }
    }
}
