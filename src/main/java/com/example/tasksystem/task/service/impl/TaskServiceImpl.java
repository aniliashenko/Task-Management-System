package com.example.tasksystem.task.service.impl;

import com.example.tasksystem.task.event.TaskAssignedEvent;
import com.example.tasksystem.task.dto.TaskRequestDto;
import com.example.tasksystem.task.dto.TaskResponseDto;
import com.example.tasksystem.task.event.TaskDeleteEvent;
import com.example.tasksystem.task.event.TaskUpdateEvent;
import com.example.tasksystem.task.filter.TaskSpecification;
import com.example.tasksystem.task.mapper.TaskMapper;
import com.example.tasksystem.project.model.Project;
import com.example.tasksystem.task.model.Task;
import com.example.tasksystem.user.model.User;
import com.example.tasksystem.project.repository.ProjectRepository;
import com.example.tasksystem.task.repository.TaskRepository;
import com.example.tasksystem.user.repository.UserRepository;
import com.example.tasksystem.task.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public TaskResponseDto create(TaskRequestDto dto) {
        final Task task = taskMapper.toEntity(dto);

        final Project project = projectRepository.findById(dto.projectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        final User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        task.setProject(project);
        task.setUser(user);

        if (user.getTelegramChatId() != null) {
            eventPublisher.publishEvent(new TaskAssignedEvent(
                    user.getId(),
                    task.getName(),
                    user.getUsername())
            );
        }

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public Page<TaskResponseDto> getAll(TaskRequestDto filter, Pageable pageable) {
        return taskRepository.findAll(TaskSpecification.filterTasks(filter), pageable)
                .map(taskMapper::toDto);
    }

    @Override
    public Optional<TaskResponseDto> getById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto);
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto dto) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (dto.projectId() != null) {
            final Project project = projectRepository.findById(dto.projectId())
                    .orElseThrow(() -> new EntityNotFoundException("Project not found"));
            task.setProject(project);
        }

        if (dto.userId() != null) {
            final User user = userRepository.findById(dto.userId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            task.setUser(user);
        }

        taskMapper.updateTaskFromDto(dto, task);
        final Task saved = taskRepository.save(task);

        final User user = userRepository.findById(saved.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getTelegramChatId() != null) {
            eventPublisher.publishEvent(new TaskUpdateEvent(
                    user.getId(),
                    user.getUsername())
            );
        }

        return taskMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        if (task.getUser().getTelegramChatId() != null) {
            eventPublisher.publishEvent(new TaskDeleteEvent(
                    task.getUser().getId(),
                    task.getName(),
                    task.getUser().getUsername())
            );
        }
        taskRepository.delete(task);
    }
}
