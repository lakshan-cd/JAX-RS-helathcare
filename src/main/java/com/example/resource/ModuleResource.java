/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Hamed
 */
import com.example.dao.ModuleDAO;
import com.example.model.Module;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/modules")
public class ModuleResource {
    private ModuleDAO moduleDAO = new ModuleDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getAllModules() {
        return moduleDAO.getAllModules();
    }

    @GET
    @Path("/{moduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Module getModuleById(@PathParam("moduleId") int moduleId) {
        return moduleDAO.getModuleById(moduleId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addModule(Module module) {
        moduleDAO.addModule(module);
    }

    @PUT
    @Path("/{moduleId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateModule(@PathParam("moduleId") int moduleId, Module updatedModule) {
        Module existingModule = moduleDAO.getModuleById(moduleId);

        if (existingModule != null) {
            updatedModule.setId(moduleId);
            moduleDAO.updateModule(updatedModule);
        }
    }

    @DELETE
    @Path("/{moduleId}")
    public void deleteModule(@PathParam("moduleId") int moduleId) {
        moduleDAO.deleteModule(moduleId);
    }

    @GET
    @Path("/teachers/{teacherId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getModulesByTeacher(@PathParam("teacherId") int teacherId) {
        List<Module> modulesByTeacher = new ArrayList<>();

        // Retrieve all modules
        List<Module> allModules = moduleDAO.getAllModules();

        // Iterate through all modules and filter modules taught by the specified teacher
        for (Module module : allModules) {
            if (module.getTeacher().getId() == teacherId) {
                modulesByTeacher.add(module);
            }
        }

        return modulesByTeacher;
    }
}
