package com.project.teacher.repo;

import com.project.config.SecurityConfig;
import com.project.teacher.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    Teacher findByEmail(String username);
}