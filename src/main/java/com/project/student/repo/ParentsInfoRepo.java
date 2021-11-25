package com.project.student.repo;

import com.project.student.models.ParentsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsInfoRepo extends JpaRepository<ParentsInfo,Integer>
{
}
