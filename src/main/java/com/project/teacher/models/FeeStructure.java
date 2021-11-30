package com.project.teacher.models;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class FeeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fee_id", nullable = false)
    private Integer fee_id;

    @ManyToOne()
    private Course course_id;
    private double fee;
    private double dicount;
}
