package com.example.tasksystem.controller;

import com.example.tasksystem.dto.comment.CommentRequestDto;
import com.example.tasksystem.dto.comment.CommentResponseDto;
import com.example.tasksystem.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto addComment(@RequestBody @Valid CommentRequestDto requestDto) {
        return commentService.addComment(requestDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<CommentResponseDto> getCommentsByTask(@RequestParam Long taskId) {
        return commentService.getCommentsByTask(taskId);
    }
}
