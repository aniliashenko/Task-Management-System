package com.example.tasksystem.dto.user;

import com.example.tasksystem.model.Role;

import java.util.Set;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        Set<Role> roles
) {}
