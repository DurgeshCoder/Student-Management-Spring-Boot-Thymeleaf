package com.project.admin.controllers;

import com.project.admin.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class Dashboard {

    @Autowired
    AdminRepo adminRepo;

    @ModelAttribute
    public void addData(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
    }


    @RequestMapping("/home")
    public String dashboard(Model m) {

        m.addAttribute("title", "Admin Dashboard");

        return "admin/home";
    }

}
