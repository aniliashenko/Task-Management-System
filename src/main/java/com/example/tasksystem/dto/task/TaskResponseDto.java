package com.example.tasksystem.dto.task;

import com.example.tasksystem.model.Task;

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
