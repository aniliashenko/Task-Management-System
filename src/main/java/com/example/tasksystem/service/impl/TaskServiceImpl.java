package com.example.tasksystem.service.impl;

import com.example.tasksystem.dto.task.TaskRequestDto;
import com.example.tasksystem.dto.task.TaskResponseDto;
import com.example.tasksystem.mapper.TaskMapper;
import com.example.tasksystem.model.Project;
import com.example.tasksystem.model.Task;
import com.example.tasksystem.model.User;
import com.example.tasksystem.repository.ProjectRepository;
import com.example.tasksystem.repository.TaskRepository;
import com.example.tasksystem.repository.UserRepository;
import com.example.tasksystem.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponseDto create(TaskRequestDto dto) {
        Task task = taskMapper.toEntity(dto);

        Project project = projectRepository.findById(dto.projectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        task.setProject(project);
        task.setUser(user);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public List<TaskResponseDto> getAllByProjectId(Long projectId) {
        return taskRepository.findAllByProjectId(projectId).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskResponseDto> getById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto);
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (dto.projectId() != null) {
            Project project = projectRepository.findById(dto.projectId())
                    .orElseThrow(() -> new EntityNotFoundException("Project not found"));
            task.setProject(project);
        }

        if (dto.userId() != null) {
            User user = userRepository.findById(dto.userId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            task.setUser(user);
        }

        taskMapper.updateTaskFromDto(dto, task);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
