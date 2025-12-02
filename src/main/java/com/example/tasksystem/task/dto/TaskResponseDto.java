package com.example.tasksystem.task.dto;

import com.example.tasksystem.task.model.Task;

import java.time.LocalDate;

public record TaskResponseDto(
        Long id,
        String name,
        String description,
        Long projectId,
        Long userId,
        Task.Priority priority,
        Task.Status status,
        LocalDate dueDate
) {
}
