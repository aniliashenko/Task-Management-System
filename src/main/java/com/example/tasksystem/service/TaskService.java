package com.example.tasksystem.service;

import com.example.tasksystem.dto.task.TaskRequestDto;
import com.example.tasksystem.dto.task.TaskResponseDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskResponseDto create(TaskRequestDto dto);

    List<TaskResponseDto> getAllByProjectId(Long projectId);

    Optional<TaskResponseDto> getById(Long id);

    TaskResponseDto update(Long id, TaskRequestDto dto);

    void delete(Long id);
}
