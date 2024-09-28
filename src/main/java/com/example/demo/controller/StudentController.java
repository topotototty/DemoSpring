package com.example.demo.controller;

import com.example.demo.model.StudentModel;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/home/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String showStudents(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sort", required = false) String sort,
            Model model) {

        List<StudentModel> students;
        if (name != null && !name.isEmpty()) {
            students = studentService.findStudentsByName(name);
        } else if ("name".equals(sort)) {
            students = studentService.sortStudentsByName();
        } else {
            students = studentService.findAllStudents();
        }
        model.addAttribute("students", students);
        return "students";
    }


    @PostMapping("/create")
    public String postCreateStudent(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
    ) {
        studentService.createStudent(new StudentModel(name, email, password));
        return "redirect:/home/students";
    }

    @PostMapping("/update")
    public String updateStudent(
            @RequestParam("id") UUID id,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam(value = "password", required = false) String password) {

        StudentModel student = studentService.findStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);

            if (password != null && !password.isEmpty()) {
                student.setPassword(password);
            }

            studentService.updateStudent(student);
        }

        return "redirect:/home/students";
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam("id") UUID id) {
        studentService.deleteStudent(id);
        return "redirect:/home/students";
    }

    @PostMapping("/delete-multiple")
    public String deleteMultipleStudents(@RequestParam("ids") List<UUID> ids) {
        studentService.deleteMultipleStudents(ids);
        return "redirect:/home/students";
    }

}
