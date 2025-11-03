package com.example.tasksystem.controller;

import com.example.tasksystem.dto.task.TaskRequestDto;
import com.example.tasksystem.dto.task.TaskResponseDto;
import com.example.tasksystem.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public TaskResponseDto create(@RequestBody @Valid TaskRequestDto requestDto) {
        return taskService.create(requestDto);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<TaskResponseDto> getAll(@RequestParam Long projectId) {
        return taskService.getAllByProjectId(projectId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public TaskResponseDto update(
            @PathVariable Long id,
            @RequestBody @Valid TaskRequestDto requestDto
    ) {
        return taskService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
