package com.example.tasksystem.mapper;

import com.example.tasksystem.dto.task.TaskRequestDto;
import com.example.tasksystem.dto.task.TaskResponseDto;
import com.example.tasksystem.model.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "projectId", source = "task.project.id")
    @Mapping(target = "userId", source = "task.user.id")
    @Mapping(target = "dueDate", source = "task.dueDate")
    TaskResponseDto toDto(Task task);

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    Task toEntity(TaskRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromDto(TaskRequestDto dto, @MappingTarget Task task);
}
