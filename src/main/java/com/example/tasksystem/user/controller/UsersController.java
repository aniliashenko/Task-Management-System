package com.example.tasksystem.user.controller;

import com.example.tasksystem.user.dto.UpdateUserDto;
import com.example.tasksystem.user.dto.UpdateUserRoleDto;
import com.example.tasksystem.user.dto.UserResponseDto;
import com.example.tasksystem.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    public UserResponseDto updateUserRole(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRoleDto dto
    ) {
        return userService.updateUserRole(id, dto);
    }

    @GetMapping("/me")
    public UserResponseDto getMyProfile() {
        return userService.getMyProfile();
    }

    @PatchMapping("/me")
    public UserResponseDto patchMyProfile(
            @RequestBody UpdateUserDto dto
    ) {
        return userService.updateMyProfile(dto);
    }
}
