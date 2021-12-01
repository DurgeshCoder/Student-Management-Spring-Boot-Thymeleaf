package com.project.teacher.models;

import com.project.student.models.Student;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id", nullable = false)
    private Integer course_id;
    private String course_name;
    private String code;
    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "course")
    private List<Subject> subject = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Student> student = new ArrayList<>();

    @OneToMany(mappedBy = "course_id")
    private List<FeeStructure> feeStructures = new ArrayList<>();
//
//    @ManyToOne
//    private Department department;

}
