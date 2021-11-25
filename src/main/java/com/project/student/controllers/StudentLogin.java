package com.project.student.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentLogin {


    @GetMapping("/student-login")
    public String login(Model m) {
        m.addAttribute("title", "Student Login");
        return "student/login";
    }

}
