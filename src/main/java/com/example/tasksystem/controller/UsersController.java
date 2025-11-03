package com.example.tasksystem.controller;

import com.example.tasksystem.dto.user.UpdateUserDto;
import com.example.tasksystem.dto.user.UpdateUserRoleDto;
import com.example.tasksystem.dto.user.UserResponseDto;
import com.example.tasksystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
