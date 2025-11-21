package com.example.tasksystem.service;

import com.example.tasksystem.dto.comment.CommentRequestDto;
import com.example.tasksystem.dto.comment.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto addComment(CommentRequestDto dto);

    List<CommentResponseDto> getCommentsByTask(Long taskId);
}
