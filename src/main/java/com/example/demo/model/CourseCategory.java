package com.example.demo.model;

public enum CourseCategory {
    PROGRAMMING("Программирование"),
    DESIGN("Дизайн"),
    MARKETING("Маркетинг"),
    MANAGEMENT("Менеджмент");

    private final String displayName;

    CourseCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}