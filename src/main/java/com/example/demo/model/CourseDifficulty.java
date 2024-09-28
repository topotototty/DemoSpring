package com.example.demo.model;

public enum CourseDifficulty {
    EASY("Легкая"),
    MEDIUM("Средняя"),
    HARD("Тяжелая");

    private final String displayName;

    CourseDifficulty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
