package com.project.teacher.repo;


import com.project.teacher.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepo extends JpaRepository<Assignment,Integer> {
}
