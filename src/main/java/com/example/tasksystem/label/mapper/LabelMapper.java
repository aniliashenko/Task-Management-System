package com.example.tasksystem.label.mapper;

import com.example.tasksystem.label.dto.LabelRequestDto;
import com.example.tasksystem.label.dto.LabelResponseDto;
import com.example.tasksystem.label.model.Label;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LabelMapper {

    LabelResponseDto toDto(Label label);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    Label toEntity(LabelRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLableFromDto(LabelRequestDto requestDto, @MappingTarget Label label);
}
