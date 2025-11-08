package com.example.tasksystem.task.event;

public record TaskAssignedEvent(
        Long userId,
        String taskName,
        String username
) {
}
