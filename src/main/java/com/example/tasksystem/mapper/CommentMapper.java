package com.example.tasksystem.mapper;

import com.example.tasksystem.dto.comment.CommentRequestDto;
import com.example.tasksystem.dto.comment.CommentResponseDto;
import com.example.tasksystem.model.Comment;
import org.mapstruct.*;

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
