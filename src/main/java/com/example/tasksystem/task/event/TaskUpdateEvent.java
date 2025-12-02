package com.example.tasksystem.task.event;

public record TaskUpdateEvent(
        Long userId,
        String username
) {
}
