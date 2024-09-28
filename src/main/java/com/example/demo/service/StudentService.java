package com.example.demo.service;

import com.example.demo.model.StudentModel;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<StudentModel> findAllStudents();
    StudentModel createStudent(StudentModel studentModel);
    StudentModel updateStudent(StudentModel studentModel);
    StudentModel findStudentById(UUID id);
    List<StudentModel> findStudentsByName(String name);
    void deleteStudent(UUID id);

    List<StudentModel> sortStudentsByName();
    void deleteMultipleStudents(List<UUID> ids);
}
