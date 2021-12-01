package com.project.teacher.controllers;

import com.project.student.models.Student;
import com.project.student.repo.StudentRepo;
import com.project.teacher.models.Assignment;
import com.project.teacher.models.Department;
import com.project.teacher.models.Subject;
import com.project.teacher.models.TimeTable;
import com.project.teacher.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/teacher")
public class TeacherDashboard {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    SubjectRepo subjectRepo;

    @Autowired
    TimeTableRepo timeTableRepo;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    AssignmentRepo assignmentRepo;

    @ModelAttribute
    public void addData(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
    }
//    show teacher profile
    @RequestMapping("/home")
    public String teacherHome(Model m, Principal principal){
        String name = principal.getName();
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
        System.out.println(this.teacherRepo.findByEmail(name));
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
        return "teacher/home";
    }

    @RequestMapping("/student")
    public String student(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
        Student student = new Student();
        m.addAttribute("student",student);
        m.addAttribute("courses",courseRepo.findAll());
        m.addAttribute("students",studentRepo.findAll());
        return "teacher/student";
    }

    @RequestMapping("/do-add-student")
    public RedirectView addStudent(@ModelAttribute("student") Student data){
        studentRepo.save(data);
        return new RedirectView("student");
    }

    @RequestMapping("/subject")
    public String subject(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
        Subject subject = new Subject();
        m.addAttribute("subject",subject);
        m.addAttribute("subjects",subjectRepo.findAll());
        m.addAttribute("courses",courseRepo.findAll());
        return "teacher/subject";
    }

    @RequestMapping("/do-add-subject")
    public RedirectView addSubject(@ModelAttribute("subject") Subject data){
        subjectRepo.save(data);
        return new RedirectView("subject");
    }

    @RequestMapping("/time-table")
    public String timeTable(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
        TimeTable timeTable = new TimeTable();
        m.addAttribute("timeTable",timeTable);
        m.addAttribute("timetables",timeTableRepo.findAll());
        m.addAttribute("subjects",subjectRepo.findAll());
        return "teacher/timeTable";
    }

    @RequestMapping("/do-add-time-table")
    public RedirectView addTimeTable(@ModelAttribute("timeTable") TimeTable data, Model m){
        timeTableRepo.save(data);
        return new RedirectView("time-table");
    }



//    @RequestMapping("/exam-report")
//    public String examination(Model m){
//        m.addAttribute("subject",studentRepo.findAll());
//        return "teacher/examReport";
//    }

    @RequestMapping("/assignment")
    public String assignment(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.teacherRepo.findByEmail(name));
        Assignment assignment = new Assignment();
        m.addAttribute("assignment",assignment);
        m.addAttribute("subjects",subjectRepo.findAll());
        m.addAttribute("assignments",assignmentRepo.findAll());
        return "teacher/assignment";
    }

    @RequestMapping("/do-add-assignment")
    public RedirectView addAssigment(@ModelAttribute("assignment") Assignment data,Model m){
        assignmentRepo.save(data);

        return new RedirectView("assignment");
    }

}
