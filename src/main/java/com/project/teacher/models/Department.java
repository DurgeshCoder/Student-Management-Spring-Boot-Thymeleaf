package com.project.teacher.models;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer department_id;

    private String department_name;
    private String hod;
    @Column(length = 1000)
    private String description;

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    @ManyToOne(optional = false)
    private Teacher teachers;

    public Teacher getTeachers() {
        return teachers;
    }

    public void setTeachers(Teacher teachers) {
        this.teachers = teachers;
    }
}
