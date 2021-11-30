package com.project.admin.controllers;

import com.project.admin.AdminRepo;
import com.project.student.models.ParentsInfo;
import com.project.student.models.Student;
import com.project.student.repo.ParentsInfoRepo;
import com.project.student.repo.StudentRepo;
import com.project.teacher.models.Course;
import com.project.teacher.models.Department;
import com.project.teacher.models.FeeStructure;
import com.project.teacher.models.Teacher;
import com.project.teacher.repo.CourseRepo;
import com.project.teacher.repo.DepartmentRepo;
import com.project.teacher.repo.FeeStructureRepo;
import com.project.teacher.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class Dashboard {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    FeeStructureRepo feeStructureRepo;

    @Autowired
    ParentsInfoRepo parentsInfoRepo;

    @ModelAttribute
    public void addData(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
    }


    @RequestMapping("/home")
    public String dashboard(Model m, Principal principal) {
        m.addAttribute("title", "Admin Dashboard");
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
        return "admin/home";
    }

    @RequestMapping("/add-student")
    public String addStudent(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
        m.addAttribute("title", "Add Student");
        Student student = new Student();
        m.addAttribute("student", student);
        m.addAttribute("allStudents",studentRepo.findAll());
        m.addAttribute("courses",courseRepo.findAll());
        return "admin/add_student";
    }

    @RequestMapping("/add-teacher")
    public String addTeacher(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
        m.addAttribute("title", "Add Teacher");
        m.addAttribute("teacher", new Teacher());
        List<Department> departments = departmentRepo.findAll();
        m.addAttribute("departments",departments);
        List<Teacher> teachers = teacherRepo.findAll();
        m.addAttribute("allTeachers",teachers);
        m.addAttribute("message","1");
        return "admin/add_teacher";
    }

    //teacher
    @PostMapping("/do-add-teacher")
    public RedirectView saveTeacher(@ModelAttribute("teacher") Teacher data, Model m) {
        Teacher t = teacherRepo.findByEmail(data.getEmail());

        if(t==null){
            data.setRole("ROLE_TEACHER");
            data.setPassword(this.bCryptPasswordEncoder.encode("teacher@123"));
            teacherRepo.save(data);
        }
        else
            m.addAttribute("message","2");
        return new RedirectView("add-teacher");
    }

    @RequestMapping("/add-course")
    public String addCourse(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
        m.addAttribute("title", "Add Course");
        Course course = new Course();
        m.addAttribute("course", course);
        List<Course> courses = courseRepo.findAll();
        m.addAttribute("courses",courses);
        return "admin/add_course";
    }

    @RequestMapping("/add-department")
    public String addDepartment(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
        m.addAttribute("title", "Add Department");
        Department department = new Department();
        m.addAttribute("department", department);
        m.addAttribute("departments",departmentRepo.findAll());
        return "admin/add_department";
    }

    @RequestMapping("/add-fee")
    public String addFeeStructure(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
        System.out.println("call is comming here");
        m.addAttribute("title", "Fee Structure");
        FeeStructure feeStructure = new FeeStructure();
        m.addAttribute("fee", feeStructure);
        m.addAttribute("fees",feeStructureRepo.findAll());
        m.addAttribute("courses", courseRepo.findAll());
        return "admin/add_fee";
    }

    @RequestMapping("/parent-info")
    public String addParentsInfo(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
        m.addAttribute("title","Parents information");
        ParentsInfo p = new ParentsInfo();
        m.addAttribute("allparents",parentsInfoRepo.findAll());
        m.addAttribute("parent",p);
        return "admin/add_parents_info";
    }
    /**
     * request handle from form
     */
    //student
    @PostMapping("/do-add-student")
    public RedirectView saveStudent(@ModelAttribute("student") Student student) {

        student.setRole("ROLE_STUDENT");
        student.setPassword(this.bCryptPasswordEncoder.encode("student@123"));
        studentRepo.save(student);
        return new RedirectView("add-student");
    }

    //course
    @PostMapping("/do-add-course")
    public RedirectView saveCourse(@ModelAttribute("course") Course course, Model m) {
        m.addAttribute("message", "addded successfully");
        System.out.println(course);
        courseRepo.save(course);
        m.addAttribute("done", true);
        return new RedirectView( "add-course");
    }
    //course
    @PostMapping("/do-add-department")
    public RedirectView saveDepartment(@ModelAttribute("department") Department department, Model m) {
        m.addAttribute("message", "Added successfully");
        m.addAttribute("departments",departmentRepo.findAll());
        System.out.println(department);
        departmentRepo.save(department);
        return new RedirectView("add-department");
    }
    //add fee
    @PostMapping("/do-add-fee")
    public RedirectView saveFeeStucture(@ModelAttribute("fee") FeeStructure feeStructure, Model m) {
        feeStructureRepo.save(feeStructure);
         return new RedirectView("add-fee");
    }
    //add parentinfo
    @PostMapping("/do-add-parents-info")
    public RedirectView saveParent(@ModelAttribute("parent") ParentsInfo parentsInfo,Model m){
        parentsInfoRepo.save(parentsInfo);
        return new RedirectView("parent-info");
    }
}