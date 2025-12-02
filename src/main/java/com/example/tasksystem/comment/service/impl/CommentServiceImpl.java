package com.example.tasksystem.comment.service.impl;

import com.example.tasksystem.comment.dto.CommentRequestDto;
import com.example.tasksystem.comment.dto.CommentResponseDto;
import com.example.tasksystem.comment.mapper.CommentMapper;
import com.example.tasksystem.comment.model.Comment;
import com.example.tasksystem.task.model.Task;
import com.example.tasksystem.user.model.User;
import com.example.tasksystem.comment.repository.CommentRepository;
import com.example.tasksystem.task.repository.TaskRepository;
import com.example.tasksystem.user.repository.UserRepository;
import com.example.tasksystem.comment.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponseDto addComment(CommentRequestDto dto) {
        final Task task = taskRepository.findById(dto.taskId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        final User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Comment comment = commentMapper.toEntity(dto);
        comment.setTask(task);
        comment.setUser(user);

        final Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    @Override
    public List<CommentResponseDto> getCommentsByTask(Long taskId) {
        final Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        return commentRepository.findAllByTask(task)
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }
}
