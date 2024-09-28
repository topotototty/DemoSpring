package com.example.demo.service;

import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryStudentImpl implements StudentService {

    private final StudentRepository studentRepository;

    public InMemoryStudentImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentModel> findAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public StudentModel createStudent(StudentModel studentModel) {
        return studentRepository.createStudent(studentModel);
    }

    @Override
    public StudentModel updateStudent(StudentModel studentModel) {
        return studentRepository.updateStudent(studentModel);
    }

    @Override
    public StudentModel findStudentById(UUID id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentRepository.deleteStudent(id);  // Логическое удаление
    }

    @Override
    public List<StudentModel> findStudentsByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    // Сортировка студентов по имени
    @Override
    public List<StudentModel> sortStudentsByName() {
        return studentRepository.sortStudentsByName();
    }

    // Множественное удаление студентов
    @Override
    public void deleteMultipleStudents(List<UUID> ids) {
        studentRepository.deleteMultipleStudents(ids);
    }
}
