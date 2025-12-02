package com.example.tasksystem.task.event;

public record TaskDeleteEvent(
        Long userId,
        String taskName,
        String username
) {
}
