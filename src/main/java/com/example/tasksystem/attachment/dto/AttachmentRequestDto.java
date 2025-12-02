package com.example.tasksystem.attachment.dto;

import org.springframework.web.multipart.MultipartFile;

public record AttachmentRequestDto(
        Long taskId,
        MultipartFile multipartFile
) {
}
