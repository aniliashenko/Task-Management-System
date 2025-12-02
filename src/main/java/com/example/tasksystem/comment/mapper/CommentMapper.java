package com.example.tasksystem.comment.mapper;

import com.example.tasksystem.comment.dto.CommentRequestDto;
import com.example.tasksystem.comment.dto.CommentResponseDto;
import com.example.tasksystem.comment.model.Comment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "taskId", source = "comment.task.id")
    @Mapping(target = "userId", source = "comment.user.id")
    @Mapping(target = "createdAt", source = "comment.timeStamp")
    CommentResponseDto toDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "timeStamp", expression = "java(java.time.LocalDateTime.now())")
    Comment toEntity(CommentRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommentFromDto(CommentRequestDto dto, @MappingTarget Comment comment);
}
