package com.project.teacher.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherLogin {
    @GetMapping("/teacher-login")
    public String login(Model m) {
        m.addAttribute("title", "Teacher Login");
        return "teacher/login";
    }
}
