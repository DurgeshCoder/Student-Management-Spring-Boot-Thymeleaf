package com.project.teacher.service;
import com.project.teacher.models.Teacher;
import com.project.teacher.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class TeacherService  implements UserDetailsService {

    @Autowired
    TeacherRepo teacherRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = this.teacherRepo.findByEmail(username);
        if (teacher == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return teacher;
    }
}