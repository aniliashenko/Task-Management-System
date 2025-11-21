package com.example.tasksystem.service;

import com.example.tasksystem.dto.project.ProjectRequestDto;
import com.example.tasksystem.dto.project.ProjectResponseDto;
import com.example.tasksystem.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ProjectResponseDto> getAll();

    Optional<ProjectResponseDto> getById(Long id);

    ProjectResponseDto create(ProjectRequestDto requestDto);

    ProjectResponseDto update(Long id, ProjectRequestDto requestDto);

    void delete(Long id);
}
