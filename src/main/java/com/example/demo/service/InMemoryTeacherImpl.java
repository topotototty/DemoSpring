package com.example.demo.service;

import com.example.demo.model.TeacherModel;
import com.example.demo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryTeacherImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public InMemoryTeacherImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TeacherModel> findAllTeachers() {
        return teacherRepository.findAllTeachers();
    }

    @Override
    public TeacherModel createTeacher(TeacherModel teacherModel) {
        return teacherRepository.createTeacher(teacherModel);
    }

    @Override
    public TeacherModel updateTeacher(TeacherModel teacherModel) {
        return teacherRepository.updateTeacher(teacherModel);
    }

    @Override
    public TeacherModel findTeacherById(UUID id) {
        return teacherRepository.findTeacherById(id);
    }

    @Override
    public void deleteTeacher(UUID id) {
        teacherRepository.deleteTeacher(id);
    }
}
