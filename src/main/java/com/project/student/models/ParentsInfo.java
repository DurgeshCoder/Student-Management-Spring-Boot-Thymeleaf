package com.project.student.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ParentsInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parentId", nullable = false)
    private Integer parentId;
    private String email;
    private String name;
    private String address;
    private String contectNo;

}
