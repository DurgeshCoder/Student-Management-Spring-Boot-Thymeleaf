package com.project;

import com.project.admin.AdminRepo;
import com.project.admin.models.Admin;
import com.project.student.models.Student;
import com.project.student.repo.StudentRepo;
import com.project.teacher.models.Teacher;
import com.project.teacher.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalTime;

@SpringBootApplication
public class StudentManageApplication implements CommandLineRunner {

    @Autowired
    public AdminRepo adminRepo;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public TeacherRepo teacherRepo;

    public static void main(String[] args) {
        SpringApplication.run(StudentManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("server running....");
//        Admin admin = new Admin();
//        admin.setEmail("admin@gmail.com");
//        admin.setPassword(this.bCryptPasswordEncoder.encode("durgesh"));
//        admin.setContactNumber("8896163869");
//        admin.setName("Durgesh");
//        admin.setRole("ADMIN");
//
//        this.adminRepo.save(admin);
//
//        Student student=new Student();
//        student.setStudentName("ANKIT");
//        student.setPassword(this.bCryptPasswordEncoder.encode("ankit"));
//        student.setEmail("student@gmail.com");
//        student.setRole("ROLE_STUDENT");
//        student.setPhone("8896163869");
//        student.setAddress("GOMTI NAGAR LKO");
//        this.studentRepo.save(student);

//        Teacher teacher=new Teacher();
//        teacher.setTeacherName("Samar");
//        teacher.setPassword(this.bCryptPasswordEncoder.encode("samar"));
//        teacher.setEmail("samar@gmail.com");
//        teacher.setRole("ROLE_TEACHER");
//        teacher.setPhone("8896163869");
//        teacher.setAddress("GOMTI NAGAR LKO");
//        this.teacherRepo.save(teacher);
    }
}
