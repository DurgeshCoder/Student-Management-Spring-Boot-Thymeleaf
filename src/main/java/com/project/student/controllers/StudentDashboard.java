package com.project.student.controllers;

import com.project.admin.AdminRepo;
import com.project.student.models.Student;
import com.project.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/student")
public class StudentDashboard {

    @Autowired
    StudentRepo studentRepo;

    @ModelAttribute
    public void addData(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.studentRepo.findByEmail(name));
    }


    @RequestMapping("/home")
    public String dashboard(Model m) {
        m.addAttribute("title", "Student Dashboard");
        return "student/home";
    }

}
