package com.example.tasksystem.dto.attachment;

import java.time.LocalDateTime;

public record AttachmentResponseDto(
        Long taskId,
        String dropBoxFileId,
        String fileName,
        LocalDateTime uploadDate
) {
}
