package com.example.tasksystem.mapper;

import com.example.tasksystem.dto.label.LabelRequestDto;
import com.example.tasksystem.dto.label.LabelResponseDto;
import com.example.tasksystem.model.Label;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LabelMapper {

    LabelResponseDto toDto(Label label);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    Label toEntity(LabelRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLableFromDto(LabelRequestDto requestDto, @MappingTarget Label label);
}
