package com.example.tasksystem.task.mapper;

import com.example.tasksystem.task.dto.TaskRequestDto;
import com.example.tasksystem.task.dto.TaskResponseDto;
import com.example.tasksystem.task.model.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
