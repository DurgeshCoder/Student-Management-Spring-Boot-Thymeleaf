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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id", nullable = false)
    private Integer subject_id;
    private String subjectName;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "subject")
    private List<Assignment> teachers = new ArrayList<>();
}
