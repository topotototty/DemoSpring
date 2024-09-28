package com.example.demo.model;

public class CourseModel {

    private Long id;
    private String name;
    private CourseCategory category;
    private CourseDifficulty difficulty;

    public CourseModel(String name, CourseCategory category, CourseDifficulty difficulty) {
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public void setCategory(CourseCategory category) {
        this.category = category;
    }

    public CourseDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(CourseDifficulty difficulty) {
        this.difficulty = difficulty;
    }
}

