package com.project.student.controllers;

import com.project.admin.AdminRepo;
import com.project.student.models.Student;
import com.project.student.repo.StudentRepo;
import com.project.teacher.models.Assignment;
import com.project.teacher.models.Course;
import com.project.teacher.models.FeeStructure;
import com.project.teacher.models.Subject;
import com.project.teacher.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentDashboard {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    SubjectRepo subjectRepo;

    @Autowired
    TimeTableRepo timeTableRepo;

    @Autowired
    AssignmentRepo assignmentRepo;

    @Autowired
    FeeStructureRepo feeStructureRepo;

    @ModelAttribute
    public void addData(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.studentRepo.findByEmail(name));
    }

    @RequestMapping("/home")
    public String dashboard(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.studentRepo.findByEmail(name));
        m.addAttribute("title", "Student Dashboard");
        return "student/home";
    }

    @RequestMapping("/subject")
    public String subject(Model m,Principal principal) {
        m.addAttribute("title", "Student Subject");
        String name = principal.getName();
        List<Subject> subjects = this.studentRepo.findByEmail(name).getCourse().getSubject();
        m.addAttribute("subjects",subjects);
        return "student/subject";
    }

    @RequestMapping("/time-table")
    public String timeTable(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.studentRepo.findByEmail(name));

//        names.stream().filter(s->s.startsWith("S")).collect(Collectors.toList());
//          this.studentRepo.findByEmail(name).getCourse().getSubject().stream().filter(s->s.equals(r)) ;
//           this.timeTableRepo.findAll().stream().filter(t->t.getSubject().getSubject_id()==)
        m.addAttribute("timetables",timeTableRepo.findAll());
        return "student/timeTable";
    }
    @RequestMapping("/assignment")
    public String assignment(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.studentRepo.findByEmail(name));
        m.addAttribute("title","Your assigment");
        ArrayList<Assignment> list=new ArrayList<>();
        this.studentRepo.findByEmail(name).getCourse().getSubject().forEach((e)->{
            e.getAssignments().forEach(assignment -> {
                list.add(assignment);
            });
        });
        m.addAttribute("assignments",list);
        return "student/assignment";
    }

    @RequestMapping("/fee")
    public String FeeStructure(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.studentRepo.findByEmail(name));
        m.addAttribute("title", "Fee Structure");
        m.addAttribute("fees",this.studentRepo.findByEmail(name).getCourse().getFeeStructures());
        m.addAttribute("courses", courseRepo.findAll());
        return "student/fee";
    }
}
