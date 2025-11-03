package com.example.tasksystem.mapper;

import com.example.tasksystem.dto.project.ProjectRequestDto;
import com.example.tasksystem.dto.project.ProjectResponseDto;
import com.example.tasksystem.model.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponseDto toDto(Project project);

    @Mapping(target = "deleted", constant = "false")
    Project toEntity(ProjectRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromDto(ProjectRequestDto requestDto, @MappingTarget Project project);
}
