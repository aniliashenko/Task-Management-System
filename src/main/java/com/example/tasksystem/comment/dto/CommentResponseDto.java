package com.example.tasksystem.comment.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        Long taskId,
        Long userId,
        String content,
        LocalDateTime createdAt
) {
}
