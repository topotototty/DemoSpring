package com.example.demo.repository;

import com.example.demo.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class StudentRepository {
    private final List<StudentModel> STUDENTS = new ArrayList<>();

    public List<StudentModel> findAllStudents() {
        return STUDENTS.stream()
                .filter(student -> !student.isDeleted())
                .collect(Collectors.toList());
    }

    public StudentModel findStudentById(UUID id) {
        return STUDENTS.stream()
                .filter(student -> student.getId().equals(id) && !student.isDeleted())
                .findFirst()
                .orElse(null);
    }

    public StudentModel createStudent(StudentModel student) {
        STUDENTS.add(student);
        return student;
    }

    public StudentModel updateStudent(StudentModel student) {
        var studentIndex = IntStream.range(0, STUDENTS.size())
                .filter(index -> STUDENTS.get(index).getId().equals(student.getId()))
                .findFirst()
                .orElse(-1);

        if (studentIndex == -1) {
            return null;
        }

        STUDENTS.set(studentIndex, student);
        return student;
    }

    // Логическое удаление
    public void deleteStudent(UUID id) {
        StudentModel student = findStudentById(id);
        if (student != null) {
            student.setDeleted(true);
        }
    }

    public void physicallyDeleteStudent(UUID id) {
        StudentModel student = findStudentById(id);
        if (student != null) {
            STUDENTS.remove(student);
        }
    }

    // Поиск студентов по имени
    public List<StudentModel> findStudentByName(String name) {
        return STUDENTS.stream()
                .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()) && !student.isDeleted())
                .collect(Collectors.toList());
    }

    // Сортировка студентов по имени
    public List<StudentModel> sortStudentsByName() {
        return STUDENTS.stream()
                .filter(student -> !student.isDeleted())
                .sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
                .collect(Collectors.toList());
    }

    // Множественное логическое удаление
    public void deleteMultipleStudents(List<UUID> ids) {
        ids.forEach(this::deleteStudent);
    }
}
