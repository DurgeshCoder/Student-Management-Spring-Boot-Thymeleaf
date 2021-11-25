package com.project.student.service;

import com.project.student.models.Student;
import com.project.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements UserDetailsService {

    @Autowired
    StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = this.studentRepo.findByEmail(username);
        if (student == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return student;

    }
}
