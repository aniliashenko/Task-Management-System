package com.example.tasksystem.attachment.service.impl;

import com.example.tasksystem.attachment.dto.AttachmentResponseDto;
import com.example.tasksystem.attachment.mapper.AttachmentMapper;
import com.example.tasksystem.attachment.model.Attachment;
import com.example.tasksystem.task.model.Task;
import com.example.tasksystem.attachment.repository.AttachmentRepository;
import com.example.tasksystem.task.repository.TaskRepository;
import com.example.tasksystem.attachment.service.AttachmentService;
import com.example.tasksystem.dropbox.service.DropboxService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;
    private final TaskRepository taskRepository;
    private final DropboxService dropboxService;

    @Override
    public AttachmentResponseDto create(Long taskId, MultipartFile file) throws Exception {
        final Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        final String dropboxPath = dropboxService.uploadFile(file.getOriginalFilename(),
                file.getInputStream(),
                file.getSize());

        final Attachment attachment = new Attachment();
        attachment.setTask(task);
        attachment.setSize(file.getSize());
        attachment.setUploadDate(LocalDateTime.now());
        attachment.setFileName(file.getOriginalFilename());
        attachment.setDropBoxFileId(dropboxPath);

        final Attachment savedAttachment = attachmentRepository.save(attachment);
        return attachmentMapper.toDto(savedAttachment);
    }

    @Override
    public List<AttachmentResponseDto> getAttachmentsFromTaskId(Long taskId) {
        final Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return attachmentRepository.findAllByTask(task)
                .stream()
                .map(attachmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
