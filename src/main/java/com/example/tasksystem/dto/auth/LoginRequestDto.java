package com.example.tasksystem.dto.auth;

public record LoginRequestDto(
        String username,
        String password
) {
}
