package com.example.tasksystem.project.service;

import com.example.tasksystem.project.dto.ProjectRequestDto;
import com.example.tasksystem.project.dto.ProjectResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectService {
    Page<ProjectResponseDto> getAll(ProjectRequestDto filter, Pageable pageable);

    Optional<ProjectResponseDto> getById(Long id);

    ProjectResponseDto create(ProjectRequestDto requestDto);

    ProjectResponseDto update(Long id, ProjectRequestDto requestDto);

    void delete(Long id);
}
