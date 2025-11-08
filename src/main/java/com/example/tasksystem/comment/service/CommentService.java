package com.example.tasksystem.comment.service;

import com.example.tasksystem.comment.dto.CommentRequestDto;
import com.example.tasksystem.comment.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto addComment(CommentRequestDto dto);

    List<CommentResponseDto> getCommentsByTask(Long taskId);
}
