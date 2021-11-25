package com.project.teacher.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer department_id;

    private String departmentName;
    private String hod;
    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "department")
    private List<Teacher> teachers = new ArrayList<>();


}
