package com.example.demo.service;

import com.example.demo.model.CourseCategory;
import com.example.demo.model.CourseDifficulty;
import com.example.demo.model.CourseModel;

import java.util.List;

public interface CourseService {
    List<CourseModel> findAllCourses();
    CourseModel createCourse(CourseModel courseModel);
    CourseModel findCourseById(Long id);
    CourseModel updateCourse(CourseModel courseModel);
    void deleteCourse(Long id);
    List<CourseModel> findCoursesByName(String name);
    List<CourseModel> sortCoursesByName();

    // Фильтрация по категории и сложности
    List<CourseModel> findCoursesByCategory(CourseCategory category);
    List<CourseModel> findCoursesByDifficulty(CourseDifficulty difficulty);
    List<CourseModel> findCoursesByCategoryAndDifficulty(CourseCategory category, CourseDifficulty difficulty);
}
