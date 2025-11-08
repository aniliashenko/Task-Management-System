package com.example.tasksystem.project.service.impl;

import com.example.tasksystem.project.dto.ProjectRequestDto;
import com.example.tasksystem.project.dto.ProjectResponseDto;
import com.example.tasksystem.project.filter.ProjectSpecification;
import com.example.tasksystem.project.mapper.ProjectMapper;
import com.example.tasksystem.project.model.Project;
import com.example.tasksystem.project.repository.ProjectRepository;
import com.example.tasksystem.project.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public Page<ProjectResponseDto> getAll(ProjectRequestDto filter, Pageable pageable) {
        return projectRepository.findAll(ProjectSpecification.filterProjects(filter), pageable)
                .map(projectMapper::toDto);
    }

    @Override
    @Transactional
    public Optional<ProjectResponseDto> getById(Long id) {
        final Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + id + " not found"));

        return Optional.ofNullable(projectMapper.toDto(project));
    }

    @Override
    public ProjectResponseDto create(ProjectRequestDto requestDto) {
        final Project entity = projectMapper.toEntity(requestDto);
        final Project savedEntity = projectRepository.save(entity);
        return projectMapper.toDto(savedEntity);
    }

    @Override
    public ProjectResponseDto update(Long id, ProjectRequestDto requestDto) {
        final Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        projectMapper.updateProjectFromDto(requestDto, project);

        final Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    @Override
    public void delete(Long id) {
        final Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        projectRepository.delete(project);
    }
}
