package com.project.teacher.models;


import lombok.Data;

import javax.persistence.*;

@Data
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

    public Integer getFee_id() {
        return fee_id;
    }

    public void setFee_id(Integer fee_id) {
        this.fee_id = fee_id;
    }
}
