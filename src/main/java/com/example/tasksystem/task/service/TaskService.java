package com.example.tasksystem.task.service;

import com.example.tasksystem.task.dto.TaskRequestDto;
import com.example.tasksystem.task.dto.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskService {

    TaskResponseDto create(TaskRequestDto dto);

    Page<TaskResponseDto> getAll(TaskRequestDto filter, Pageable pageable);

    Optional<TaskResponseDto> getById(Long id);

    TaskResponseDto update(Long id, TaskRequestDto dto);

    void delete(Long id);
}
