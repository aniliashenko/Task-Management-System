package com.example.tasksystem.service.impl;

import com.example.tasksystem.dto.project.ProjectRequestDto;
import com.example.tasksystem.dto.project.ProjectResponseDto;
import com.example.tasksystem.mapper.ProjectMapper;
import com.example.tasksystem.model.Project;
import com.example.tasksystem.repository.ProjectRepository;
import com.example.tasksystem.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public List<ProjectResponseDto> getAll() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public Optional<ProjectResponseDto> getById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + id + " not found"));

        return Optional.ofNullable(projectMapper.toDto(project));
    }

    @Override
    public ProjectResponseDto create(ProjectRequestDto requestDto) {
        Project entity = projectMapper.toEntity(requestDto);
        Project savedEntity = projectRepository.save(entity);
        return projectMapper.toDto(savedEntity);
    }

    @Override
    public ProjectResponseDto update(Long id, ProjectRequestDto requestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        projectMapper.updateProjectFromDto(requestDto, project);

        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    @Override
    public void delete(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        projectRepository.delete(project);
    }
}
