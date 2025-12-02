package com.example.tasksystem.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @Size(max = 100) String firstName,
        @Size(max = 100) String lastName,
        @Email String email
) {
}
