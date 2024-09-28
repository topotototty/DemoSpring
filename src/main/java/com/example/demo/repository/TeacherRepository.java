package com.example.demo.repository;

import com.example.demo.model.StudentModel;
import com.example.demo.model.TeacherModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@Repository
public class TeacherRepository {

    private final List<TeacherModel> TEACHERS = new ArrayList<>();

    public List<TeacherModel> findAllTeachers() {
        return new ArrayList<>(TEACHERS);
    }

    public TeacherModel findTeacherById(UUID id) {
        return TEACHERS.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public TeacherModel createTeacher(TeacherModel teacherModel) {
        teacherModel.setId(UUID.randomUUID());
        TEACHERS.add(teacherModel);
        return teacherModel;
    }

    public TeacherModel updateTeacher(TeacherModel updatedTeacher) {
        var teacherIndex = IntStream.range(0, TEACHERS.size())
                .filter(index -> TEACHERS.get(index).getId().equals(updatedTeacher.getId()))
                .findFirst()
                .orElse(-1);

        if (teacherIndex == -1) {
            return null;
        }

        TEACHERS.set(teacherIndex, updatedTeacher);
        return updatedTeacher;
    }

    public boolean deleteTeacher(UUID id) {
        return TEACHERS.removeIf(teacherModel -> teacherModel.getId().equals(id));
    }
}
