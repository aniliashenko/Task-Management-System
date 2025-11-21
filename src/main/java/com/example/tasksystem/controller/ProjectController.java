package com.example.tasksystem.controller;

import com.example.tasksystem.dto.project.ProjectRequestDto;
import com.example.tasksystem.dto.project.ProjectResponseDto;
import com.example.tasksystem.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<ProjectResponseDto> getAll() {
        return projectService.getAll().stream()
                .toList();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public Optional<ProjectResponseDto> getById(@PathVariable Long id) {
        return projectService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProjectResponseDto create(@RequestBody @Valid ProjectRequestDto requestDto) {
        return projectService.create(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProjectResponseDto update(
            @PathVariable Long id,
            @RequestBody @Valid ProjectRequestDto requestDto
    ) {
        return projectService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
