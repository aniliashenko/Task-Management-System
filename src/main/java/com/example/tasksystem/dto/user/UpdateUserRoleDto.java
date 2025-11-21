package com.example.tasksystem.dto.user;

import com.example.tasksystem.model.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateUserRoleDto(
        @NotNull(message = "Roles cannot be null")
        Set<Role> roles
) {
}
