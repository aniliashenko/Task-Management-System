package com.example.tasksystem.user.mapper;

import com.example.tasksystem.user.dto.UpdateUserDto;
import com.example.tasksystem.user.dto.UserResponseDto;
import com.example.tasksystem.user.model.User;
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
