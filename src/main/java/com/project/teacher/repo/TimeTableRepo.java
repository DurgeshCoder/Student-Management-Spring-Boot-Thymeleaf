package com.project.teacher.repo;

import com.project.teacher.models.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeTableRepo extends JpaRepository<TimeTable,Integer> {

}
