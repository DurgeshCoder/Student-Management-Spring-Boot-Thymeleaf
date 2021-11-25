package com.project.teacher.controllers;

import com.project.teacher.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/teacher")
public class TeacherDashboard {

    @Autowired
    TeacherRepo teacherRepo;

    @ModelAttribute
    public void addData(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
    }
    @RequestMapping("/home")
    public String teacherHome(){
        return "teacher/home";
    }
}
