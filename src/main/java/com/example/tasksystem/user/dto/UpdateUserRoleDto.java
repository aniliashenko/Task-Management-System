package com.example.tasksystem.user.dto;

import com.example.tasksystem.user.model.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateUserRoleDto(
        @NotNull(message = "Roles cannot be null")
        Set<Role> roles
) {
}
