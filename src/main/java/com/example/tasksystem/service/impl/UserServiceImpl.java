package com.example.tasksystem.service.impl;

import com.example.tasksystem.dto.user.UpdateUserDto;
import com.example.tasksystem.dto.user.UpdateUserRoleDto;
import com.example.tasksystem.dto.user.UserResponseDto;
import com.example.tasksystem.mapper.UserMapper;
import com.example.tasksystem.model.Role;
import com.example.tasksystem.model.User;
import com.example.tasksystem.repository.UserRepository;
import com.example.tasksystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto updateUserRole(Long userId, UpdateUserRoleDto updateRoleDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<Role> roles = updateRoleDto.roles();
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Roles must not be empty");
        }

        user.setRoles(roles);
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional()
    public UserResponseDto getMyProfile() {
        User me = getCurrentUserEntity();
        return userMapper.toDto(me);
    }

    @Override
    @Transactional
    public UserResponseDto updateMyProfile(UpdateUserDto userUpdateDto) {
        User me = getCurrentUserEntity();

        userMapper.updateFromDto(userUpdateDto, me);

        User saved = userRepository.save(me);
        return userMapper.toDto(saved);
    }

    private User getCurrentUserEntity() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new EntityNotFoundException("Current user not authenticated");
        }

        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + auth.getName()));
    }
}
