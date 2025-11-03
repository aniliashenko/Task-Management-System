package com.example.tasksystem.mapper;

import com.example.tasksystem.dto.user.UpdateUserDto;
import com.example.tasksystem.dto.user.UserResponseDto;
import com.example.tasksystem.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UpdateUserDto dto, @MappingTarget User user);
}
