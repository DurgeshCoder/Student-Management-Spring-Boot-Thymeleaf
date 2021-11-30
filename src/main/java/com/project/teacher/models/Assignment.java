package com.project.teacher.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_id", nullable = false)
    private Integer assignment_id;
    private String title;
    private String lastdate;
    @ManyToOne
    private Subject subject;
    private String content;
}