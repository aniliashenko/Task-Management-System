package com.example.tasksystem.dto.attachment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AttachmentRequestDto(
        @NotNull Long taskId,
        @NotNull String dropBoxFileId,
        @NotBlank String fileName,
        LocalDateTime uploadDate
) {
}
