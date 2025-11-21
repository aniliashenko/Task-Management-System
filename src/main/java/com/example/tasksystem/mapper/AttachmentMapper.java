package com.example.tasksystem.mapper;

import com.example.tasksystem.dto.attachment.AttachmentRequestDto;
import com.example.tasksystem.dto.attachment.AttachmentResponseDto;
import com.example.tasksystem.model.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

    @Mapping(target = "taskId", source = "attachment.task.id")
    @Mapping(target = "uploadDate", source = "attachment.uploadDate")
    AttachmentResponseDto toDto(Attachment attachment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "uploadDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isDeleted", constant = "false")
    Attachment toEntity(AttachmentRequestDto requestDto);
}
