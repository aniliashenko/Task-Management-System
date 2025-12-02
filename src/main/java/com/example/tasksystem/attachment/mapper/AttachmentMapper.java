package com.example.tasksystem.attachment.mapper;

import com.example.tasksystem.attachment.dto.AttachmentResponseDto;
import com.example.tasksystem.attachment.model.Attachment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentResponseDto toDto(Attachment attachment);
}
