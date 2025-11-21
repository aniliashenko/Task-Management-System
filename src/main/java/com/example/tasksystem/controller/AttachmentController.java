package com.example.tasksystem.controller;

import com.example.tasksystem.dto.attachment.AttachmentRequestDto;
import com.example.tasksystem.dto.attachment.AttachmentResponseDto;
import com.example.tasksystem.service.AttachmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttachmentResponseDto create(@RequestBody @Valid AttachmentRequestDto requestDto) {
        return attachmentService.create(requestDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<AttachmentResponseDto> getAttachmentsFromTaskId(@RequestParam Long taskId) {
        return attachmentService.getAttachmentsFromTaskId(taskId);
    }
}
