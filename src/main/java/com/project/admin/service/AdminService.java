package com.project.admin.service;

import com.project.admin.AdminRepo;
import com.project.admin.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = this.adminRepo.findByEmail(username);
        if (admin == null) {
            throw new UsernameNotFoundException("No user found");
        }

        return admin;


    }
}
