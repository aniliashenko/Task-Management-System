package com.example.tasksystem.task.controller;

import com.example.tasksystem.task.dto.TaskRequestDto;
import com.example.tasksystem.task.dto.TaskResponseDto;
import com.example.tasksystem.task.model.Task;
import com.example.tasksystem.task.service.TaskService;
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
    public Page<TaskResponseDto> getAll(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String description,
                                        @RequestParam(required = false) Long projectId,
                                        @RequestParam(required = false) Long userId,
                                        @RequestParam(required = false) Task.Priority priority,
                                        @RequestParam(required = false) Task.Status status,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                                        @RequestParam(required = false) String search,
                                        Pageable pageable) {
        final TaskRequestDto filter = new TaskRequestDto(name, description, projectId, userId, priority, status, dueDate, search);
        return taskService.getAll(filter, pageable);
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
