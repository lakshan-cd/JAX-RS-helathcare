/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Teacher;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private static List<Teacher> teachers = new ArrayList<>();

    static {
        teachers.add(new Teacher(1, "Mr. Smith"));
        teachers.add(new Teacher(2, "Ms. Johnson"));
        // Add more teachers as needed
    }

    public List<Teacher> getAllTeachers() {
        return teachers;
    }

    public Teacher getTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void updateTeacher(Teacher updatedTeacher) {
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            if (teacher.getId() == updatedTeacher.getId()) {
                teachers.set(i, updatedTeacher);
                return;
            }
        }
    }

    public void deleteTeacher(int id) {
        teachers.removeIf(teacher -> teacher.getId() == id);
    }
}
