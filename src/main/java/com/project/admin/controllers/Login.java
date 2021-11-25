package com.project.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {


    @GetMapping("/admin-login")

    public String login(Model m)
    {
        m.addAttribute("title","Admin Login");
        return "admin/login";
    }

}
