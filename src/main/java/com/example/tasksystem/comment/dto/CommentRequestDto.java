package com.example.tasksystem.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequestDto(
        @NotNull Long taskId,
        @NotNull Long userId,
        @NotBlank String content
) {
}
