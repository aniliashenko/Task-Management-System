package com.example.tasksystem.user.dto;

import com.example.tasksystem.user.model.Role;

import java.util.Set;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        Set<Role> roles
) {
}
