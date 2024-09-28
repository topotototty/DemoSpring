package com.example.demo.controller;

import com.example.demo.model.CourseCategory;
import com.example.demo.model.CourseDifficulty;
import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/home/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String showAllCourses(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "category", required = false) CourseCategory category,
            @RequestParam(name = "difficulty", required = false) CourseDifficulty difficulty,
            Model model) {

        List<CourseModel> courses;

        if (category != null && difficulty != null) {
            courses = courseService.findCoursesByCategoryAndDifficulty(category, difficulty);
        } else if (category != null) {
            courses = courseService.findCoursesByCategory(category);
        } else if (difficulty != null) {
            courses = courseService.findCoursesByDifficulty(difficulty);
        } else if (name != null && !name.isEmpty()) {
            courses = courseService.findCoursesByName(name);
        } else if ("name".equals(sort)) {
            courses = courseService.sortCoursesByName();
        } else {
            courses = courseService.findAllCourses();
        }

        model.addAttribute("courses", courses);
        model.addAttribute("categories", CourseCategory.values());
        model.addAttribute("difficulties", CourseDifficulty.values());

        return "courses";
    }


    @PostMapping("/create")
    public String createCourse(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "category") CourseCategory category,
            @RequestParam(name = "difficulty") CourseDifficulty difficulty) {

        courseService.createCourse(new CourseModel(name, category, difficulty));
        return "redirect:/home/courses";  // Перенаправляем на правильный маршрут
    }

    @PostMapping("/update")
    public String updateCourse(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("category") CourseCategory category,
            @RequestParam("difficulty") CourseDifficulty difficulty
    ) {
        CourseModel course = courseService.findCourseById(id);
        if (course != null) {
            course.setName(name);
            course.setCategory(category);
            course.setDifficulty(difficulty);

            courseService.updateCourse(course);
        }

        return "redirect:/home/courses";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/home/courses";
    }

}

