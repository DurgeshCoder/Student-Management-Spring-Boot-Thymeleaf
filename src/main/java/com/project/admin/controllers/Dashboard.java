package com.project.admin.controllers;

import com.project.admin.AdminRepo;
import com.project.student.models.Student;
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

    @ModelAttribute
    public void addData(Model m, Principal principal) {
        String name = principal.getName();
        m.addAttribute("user", this.adminRepo.findByEmail(name));
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/home")
    public String dashboard(Model m) {

        m.addAttribute("title", "Admin Dashboard");
        return "admin/home";
    }

    @RequestMapping("/add-student")
    public String addStudent(Model m) {
        m.addAttribute("title", "Add Student");
        Student student = new Student();
        m.addAttribute("student", student);
//        m.addAttribute("allStudents",studentRepo.findAll());
        return "admin/add_student";
    }

    @RequestMapping("/add-teacher")
    public String addTeacher(Model m) {
        m.addAttribute("title", "Add Teacher");
        m.addAttribute("teacher", new Teacher());
        m.addAttribute("departments", departmentRepo.findAll());
        m.addAttribute("allTeachers",teacherRepo.findAll());
        return "admin/add_teacher";
    }

    @RequestMapping("/add-course")
    public String addCourse(Model m) {
        m.addAttribute("title", "Add Course");
        Course course = new Course();
        m.addAttribute("course", course);
        m.addAttribute("courses",courseRepo.findAll());
        return "admin/add_course";
    }

    @RequestMapping("/add-department")
    public String addDepartment(Model m) {
        m.addAttribute("title", "Add Department");
        Department department = new Department();
        m.addAttribute("department", department);
        List<Department> departments = departmentRepo.findAll();
        m.addAttribute("departments",departments);
        return "admin/add_department";
    }

    @RequestMapping("/add-fee")
    public String addFeeStructure(Model m) {
        System.out.println("call is comming here");
        m.addAttribute("title", "Fee Structure");
        FeeStructure fee = new FeeStructure();
        m.addAttribute("fee", fee);
        m.addAttribute("courses", courseRepo.findAll());
        return "admin/add_fee";
    }

    /**
     * request handle from form
     */

//student
    @PostMapping("/do-add-student")
    public String saveStudent(@ModelAttribute("student") Student student, Model m) {
        m.addAttribute("message", "addded successfully");
        student.setRole("ROLE_STUDENT");
        student.setPassword("student123");
        studentRepo.save(student);
        System.out.println(student);
        return "admin/add_student";
    }

    //teacher
    @PostMapping("/do-add-teacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, Model m) {
        teacher.setRole("ROLE_TEACHER");
        teacher.setPassword("student123");
        teacherRepo.save(teacher);
        m.addAttribute("message", "addded successfully");
        System.out.println(teacher);
        List<Department> dep = departmentRepo.findAll();
        System.out.println(dep);
        m.addAttribute("departments", dep);
        return "admin/add_teacher";
    }

    //course
    @PostMapping("/do-add-course")
    public String saveCourse(@ModelAttribute("course") Course course, Model m) {
        m.addAttribute("message", "addded successfully");
        System.out.println(course);
        courseRepo.save(course);
        m.addAttribute("done", true);
        return "admin/add_course";
    }

    //course
    @PostMapping("/do-add-department")
    public RedirectView saveDepartment(@ModelAttribute("department") Department department, Model m) {
        m.addAttribute("message", "addded successfully");
        System.out.println(department);
        departmentRepo.save(department);
        return new RedirectView("/admin/add-department");
    }

    @PostMapping("/do-add-fee")
    public RedirectView saveFeeStucture(@ModelAttribute("fee") FeeStructure feeStructure, Model m) {
        m.addAttribute("message", "addded successfully");
        System.out.println(feeStructure);
        feeStructureRepo.save(feeStructure);
         return new RedirectView("admin/add_fee");
    }

}
