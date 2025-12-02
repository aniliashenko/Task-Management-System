package com.example.tasksystem.project.dto;

import com.example.tasksystem.project.model.Project;

import java.time.LocalDate;

public record ProjectResponseDto(
        Long id,
        String name,
        String description,
        Project.Status status,
        LocalDate startDate,
        LocalDate endDate
) {
}
