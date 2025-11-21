package com.example.tasksystem.service;

import com.example.tasksystem.dto.attachment.AttachmentRequestDto;
import com.example.tasksystem.dto.attachment.AttachmentResponseDto;

import java.util.List;

public interface AttachmentService {

    AttachmentResponseDto create(AttachmentRequestDto requestDto);

    List<AttachmentResponseDto> getAttachmentsFromTaskId(Long taskId);
}
