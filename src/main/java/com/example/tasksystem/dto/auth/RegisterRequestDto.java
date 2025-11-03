package com.example.tasksystem.dto.auth;

public record RegisterRequestDto(
        String username,
        String password,
        String email,
        String firstName,
        String lastName
) {
}
