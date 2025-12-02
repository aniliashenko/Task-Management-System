package com.example.tasksystem.user.dto;

import com.example.tasksystem.user.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserRequestDto(

        @NotBlank(message = "Username can not be empty")
        @Size(max = 25, message = "Username can not be longer than 25 symbols")
        String username,

        @NotBlank(message = "Password can not be empty")
        @Size(max = 25, message = "Password can not be longer than 25 symbols")
        String password,

        @NotBlank(message = "Email can not be empty")
        String email,

        String firstName,

        String lastName,

        Set<Role> roles
) {
}
