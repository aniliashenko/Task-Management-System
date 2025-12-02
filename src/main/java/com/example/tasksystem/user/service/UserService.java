package com.example.tasksystem.user.service;

import com.example.tasksystem.user.dto.UpdateUserDto;
import com.example.tasksystem.user.dto.UpdateUserRoleDto;
import com.example.tasksystem.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto updateUserRole(Long userId, UpdateUserRoleDto updateRoleDto);

    UserResponseDto getMyProfile();

    UserResponseDto updateMyProfile(UpdateUserDto userUpdateDto);

    Long getChatIdById(Long userId);

    void updateChatIdById(Long userId, Long chatId);
}
