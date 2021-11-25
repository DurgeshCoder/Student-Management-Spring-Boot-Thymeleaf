package com.project.student.repo;

import com.project.config.SecurityConfig;
import com.project.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    Student findByEmail(String username);

}
