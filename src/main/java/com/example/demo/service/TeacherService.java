package com.example.demo.service;

import com.example.demo.model.TeacherModel;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    List<TeacherModel> findAllTeachers();
    TeacherModel createTeacher(TeacherModel teacherModel);

    TeacherModel updateTeacher(TeacherModel teacherModel);

    TeacherModel findTeacherById(UUID id);
    void deleteTeacher(UUID id);
}
