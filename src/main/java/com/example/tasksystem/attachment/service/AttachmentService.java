package com.example.tasksystem.attachment.service;

import com.example.tasksystem.attachment.dto.AttachmentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {

    AttachmentResponseDto create(Long taskId, MultipartFile file) throws Exception;

    List<AttachmentResponseDto> getAttachmentsFromTaskId(Long taskId);
}
