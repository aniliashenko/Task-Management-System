package com.example.tasksystem.dto.comment;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        Long taskId,
        Long userId,
        String content,
        LocalDateTime createdAt
) {
}
