/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Hamed
 */

import com.example.dao.TeacherDAO;
import com.example.model.Teacher;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/teachers")
public class TeacherResource {
    private TeacherDAO teacherDAO = new TeacherDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Teacher> getAllTeachers() {
        return teacherDAO.getAllTeachers();
    }

    @GET
    @Path("/{teacherId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getTeacherById(@PathParam("teacherId") int teacherId) {
        return teacherDAO.getTeacherById(teacherId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTeacher(Teacher teacher) {
        teacherDAO.addTeacher(teacher);
    }

    @PUT
    @Path("/{teacherId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTeacher(@PathParam("teacherId") int teacherId, Teacher updatedTeacher) {
        Teacher existingTeacher = teacherDAO.getTeacherById(teacherId);

        if (existingTeacher != null) {
            updatedTeacher.setId(teacherId);
            teacherDAO.updateTeacher(updatedTeacher);
        }
    }

    @DELETE
    @Path("/{teacherId}")
    public void deleteTeacher(@PathParam("teacherId") int teacherId) {
        teacherDAO.deleteTeacher(teacherId);
    }
}

