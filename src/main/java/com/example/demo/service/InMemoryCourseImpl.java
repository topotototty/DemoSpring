package com.example.demo.service;

import com.example.demo.model.CourseCategory;
import com.example.demo.model.CourseDifficulty;
import com.example.demo.model.CourseModel;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InMemoryCourseImpl implements CourseService {

    private final CourseRepository courseRepository;

    public InMemoryCourseImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseModel> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    @Override
    public CourseModel createCourse(CourseModel courseModel) {
        return courseRepository.createCourse(courseModel);
    }

    @Override
    public CourseModel findCourseById(Long id) {
        Optional<CourseModel> course = courseRepository.findCourseById(id);
        return course.orElse(null);
    }

    @Override
    public CourseModel updateCourse(CourseModel courseModel) {
        Optional<CourseModel> existingCourse = courseRepository.findCourseById(courseModel.getId());
        if (existingCourse.isPresent()) {
            existingCourse.get().setName(courseModel.getName());
            existingCourse.get().setCategory(courseModel.getCategory());
            existingCourse.get().setDifficulty(courseModel.getDifficulty());
            return existingCourse.get();
        }
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.findCourseById(id).ifPresent(course -> courseRepository.deleteCourse(id));
    }

    @Override
    public List<CourseModel> findCoursesByName(String name) {
        return courseRepository.findAllCourses().stream()
                .filter(course -> course.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseModel> sortCoursesByName() {
        return courseRepository.findAllCourses().stream()
                .sorted((course1, course2) -> course1.getName().compareToIgnoreCase(course2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseModel> findCoursesByCategory(CourseCategory category) {
        return courseRepository.findAllCourses().stream()
                .filter(course -> course.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseModel> findCoursesByDifficulty(CourseDifficulty difficulty) {
        return courseRepository.findAllCourses().stream()
                .filter(course -> course.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseModel> findCoursesByCategoryAndDifficulty(CourseCategory category, CourseDifficulty difficulty) {
        return courseRepository.findAllCourses().stream()
                .filter(course -> course.getCategory() == category && course.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }
}

