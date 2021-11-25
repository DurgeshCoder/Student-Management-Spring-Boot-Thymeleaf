package com.project.teacher.repo;

import com.project.teacher.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {



}
