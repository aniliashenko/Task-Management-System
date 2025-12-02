package com.example.tasksystem.task.dto;

import com.example.tasksystem.task.model.Task;

import java.time.LocalDate;

public record TaskRequestDto(
        String name,
        String description,
        Long projectId,
        Long userId,
        Task.Priority priority,
        Task.Status status,
        LocalDate dueDate,
        String search
) {
}
