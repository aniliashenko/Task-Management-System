package com.example.tasksystem.project.mapper;

import com.example.tasksystem.project.dto.ProjectRequestDto;
import com.example.tasksystem.project.dto.ProjectResponseDto;
import com.example.tasksystem.project.model.Project;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponseDto toDto(Project project);

    @Mapping(target = "deleted", constant = "false")
    Project toEntity(ProjectRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromDto(ProjectRequestDto requestDto, @MappingTarget Project project);
}
