package com.project.teacher.repo;


import com.project.teacher.models.Assignment;
import com.project.teacher.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepo extends JpaRepository<Assignment,Integer> {


}
