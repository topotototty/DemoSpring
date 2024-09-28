package com.example.demo.service;

import com.example.demo.model.TeacherModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    List<TeacherModel> findAllTeachers();
    TeacherModel createTeacher(TeacherModel teacherModel);
    TeacherModel updateTeacher(TeacherModel teacherModel);
    TeacherModel findTeacherById(UUID id);
    void deleteTeacher(UUID id);

    List<TeacherModel> findPaginatedTeachers(int page, int size);
    int getTotalTeachers();

}
