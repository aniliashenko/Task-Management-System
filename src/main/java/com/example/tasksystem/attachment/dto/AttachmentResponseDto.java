package com.example.tasksystem.attachment.dto;

public record AttachmentResponseDto(
        Long id,
        String dropBoxFileId,
        String fileName
) {
}
