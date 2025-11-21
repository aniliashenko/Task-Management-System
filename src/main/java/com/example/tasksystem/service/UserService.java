package com.example.tasksystem.service;

import com.example.tasksystem.dto.user.UpdateUserDto;
import com.example.tasksystem.dto.user.UpdateUserRoleDto;
import com.example.tasksystem.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto updateUserRole(Long userId, UpdateUserRoleDto updateRoleDto);

    UserResponseDto getMyProfile();

    UserResponseDto updateMyProfile(UpdateUserDto userUpdateDto);
}
