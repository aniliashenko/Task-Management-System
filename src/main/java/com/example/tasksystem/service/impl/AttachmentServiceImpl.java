package com.example.tasksystem.service.impl;

import com.example.tasksystem.dto.attachment.AttachmentRequestDto;
import com.example.tasksystem.dto.attachment.AttachmentResponseDto;
import com.example.tasksystem.mapper.AttachmentMapper;
import com.example.tasksystem.model.Attachment;
import com.example.tasksystem.model.Task;
import com.example.tasksystem.repository.AttachmentRepository;
import com.example.tasksystem.repository.TaskRepository;
import com.example.tasksystem.service.AttachmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;
    private final TaskRepository taskRepository;

    @Override
    public AttachmentResponseDto create(AttachmentRequestDto requestDto) {
        Task task = taskRepository.findById(requestDto.taskId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + requestDto.taskId()));

        Attachment attachment = attachmentMapper.toEntity(requestDto);
        attachment.setTask(task);

        Attachment savedAttachment = attachmentRepository.save(attachment);

        return attachmentMapper.toDto(savedAttachment);
    }

    @Override
    public List<AttachmentResponseDto> getAttachmentsFromTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        return attachmentRepository.findAllByTask(task)
                .stream()
                .map(attachmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
