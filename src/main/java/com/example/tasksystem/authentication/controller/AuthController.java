package com.example.tasksystem.authentication.controller;

import com.example.tasksystem.authentication.dto.AuthResponseDto;
import com.example.tasksystem.authentication.dto.LoginRequestDto;
import com.example.tasksystem.authentication.dto.RegisterRequestDto;
import com.example.tasksystem.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterRequestDto requestDto) {
        return authService.register(requestDto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto requestDto) {
        return authService.login(requestDto);
    }
}
