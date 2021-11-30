package com.project.teacher.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "time_table_id", nullable = false)
    private Integer timeTable_id;
    private String date;
    private String time;
    @OneToOne
    private Subject subject;
}
