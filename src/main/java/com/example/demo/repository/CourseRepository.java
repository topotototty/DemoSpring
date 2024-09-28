package com.example.demo.repository;

import com.example.demo.model.CourseModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<CourseModel> COURSES = new ArrayList<>();

    public List<CourseModel> findAllCourses() {
        return new ArrayList<>(COURSES);
    }

    public Optional<CourseModel> findCourseById(Long id) {
        return COURSES.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    public CourseModel createCourse(CourseModel courseModel) {
        if (courseModel.getId() == null) {
            courseModel.setId((long) (COURSES.size() + 1));
        }
        COURSES.add(courseModel);
        return courseModel;
    }

    public CourseModel updateCourse(CourseModel updatedCourse) {
        Optional<CourseModel> existingCourseOpt = findCourseById(updatedCourse.getId());
        if (existingCourseOpt.isPresent()) {
            CourseModel existingCourse = existingCourseOpt.get();
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setCategory(updatedCourse.getCategory());
            existingCourse.setDifficulty(updatedCourse.getDifficulty());
            return existingCourse;
        }
        return null;
    }

    public void deleteCourse(Long id) {
        COURSES.removeIf(course -> course.getId().equals(id));
    }

}
