package com.example.demo.model;

import java.util.UUID;

public class TeacherModel {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private CourseCategory course;

    public TeacherModel(String name, String email, String password, CourseCategory course) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.course = course;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CourseCategory getCourse() {
        return course;
    }

    public void setCourse(CourseCategory course) {
        this.course = course;
    }
}
