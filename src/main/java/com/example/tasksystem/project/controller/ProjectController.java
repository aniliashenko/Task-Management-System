package com.example.tasksystem.project.controller;

import com.example.tasksystem.project.dto.ProjectRequestDto;
import com.example.tasksystem.project.dto.ProjectResponseDto;
import com.example.tasksystem.project.model.Project;
import com.example.tasksystem.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public Page<ProjectResponseDto> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Project.Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String search,
            Pageable pageable) {
        final ProjectRequestDto filter = new ProjectRequestDto(name, description, status, startDate, endDate, search);
        return projectService.getAll(filter, pageable);
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
