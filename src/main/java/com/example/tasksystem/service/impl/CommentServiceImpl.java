package com.example.tasksystem.service.impl;

import com.example.tasksystem.dto.comment.CommentRequestDto;
import com.example.tasksystem.dto.comment.CommentResponseDto;
import com.example.tasksystem.mapper.CommentMapper;
import com.example.tasksystem.model.Comment;
import com.example.tasksystem.model.Task;
import com.example.tasksystem.model.User;
import com.example.tasksystem.repository.CommentRepository;
import com.example.tasksystem.repository.TaskRepository;
import com.example.tasksystem.repository.UserRepository;
import com.example.tasksystem.service.CommentService;
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
        Task task = taskRepository.findById(dto.taskId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Comment comment = commentMapper.toEntity(dto);
        comment.setTask(task);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    @Override
    public List<CommentResponseDto> getCommentsByTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        return commentRepository.findAllByTask(task)
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }
}
