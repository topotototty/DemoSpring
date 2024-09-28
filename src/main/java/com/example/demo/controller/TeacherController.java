package com.example.demo.controller;

import com.example.demo.model.TeacherModel;
import com.example.demo.model.CourseCategory;
import com.example.demo.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/home/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getAllTeachers(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {

        List<TeacherModel> teacherPage = teacherService.findPaginatedTeachers(page, size);
        int totalTeachers = teacherService.getTotalTeachers();
        int totalPages = (int) Math.ceil((double) totalTeachers / size);

        model.addAttribute("teachers", teacherPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "teachers";
    }

    @PostMapping("/create")
    public String createTeacher(@RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("course") CourseCategory course,
                                @RequestParam("password") String password) {
        TeacherModel teacher = new TeacherModel(name, email, password, course);
        teacherService.createTeacher(teacher);
        return "redirect:/home/teachers";
    }

    @PostMapping("/update")
    public String updateTeacher(@RequestParam("id") UUID id,
                                @RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("course") CourseCategory course,
                                @RequestParam(value = "password", required = false) String password) {
        // Fetch the existing teacher
        TeacherModel teacher = teacherService.findTeacherById(id);

        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setCourse(course);
        if (password != null && !password.isEmpty()) {
            teacher.setPassword(password);
        }
        teacherService.updateTeacher(teacher);
        return "redirect:/home/teachers";
    }

    @PostMapping("/delete")
    public String deleteTeacher(@RequestParam("id") UUID id) {
        teacherService.deleteTeacher(id);
        return "redirect:/home/teachers";
    }
}
