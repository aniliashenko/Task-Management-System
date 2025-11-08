package com.example.tasksystem.attachment.controller;

import com.example.tasksystem.attachment.dto.AttachmentResponseDto;
import com.example.tasksystem.attachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public AttachmentResponseDto create(
            @RequestParam("taskId") Long taskId,
            @RequestParam("file") MultipartFile file) throws Exception {
        return attachmentService.create(taskId, file);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<AttachmentResponseDto> getAttachmentsFromTaskId(@RequestParam Long taskId) {
        return attachmentService.getAttachmentsFromTaskId(taskId);
    }
}
