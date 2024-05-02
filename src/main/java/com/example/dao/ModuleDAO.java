/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Module;
import com.example.model.Teacher;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO {
//    private static List<Module> modules = new ArrayList<>();
//
//    static {
//        Teacher teacher1 = new Teacher(1, "Mr. Smith");
//        Teacher teacher2 = new Teacher(2, "Ms. Johnson");
//
//        modules.add(new Module(1, "Math", teacher1));
//        modules.add(new Module(2, "Science", teacher2));
//        // Add more modules as needed
//    }
    
    private static List<Module> modules = new ArrayList<>();

    static {

        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers = teacherDAO.getAllTeachers();


        modules.add(new Module(1, "Math", teachers.get(0)));
        modules.add(new Module(2, "Science", teachers.get(1)));
    
    }

    public List<Module> getAllModules() {
        return modules;
    }

    public Module getModuleById(int id) {
        for (Module module : modules) {
            if (module.getId() == id) {
                return module;
            }
        }
        return null;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public void updateModule(Module updatedModule) {
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            if (module.getId() == updatedModule.getId()) {
                modules.set(i, updatedModule);
                return;
            }
        }
    }

    public void deleteModule(int id) {
        modules.removeIf(module -> module.getId() == id);
    }
}

