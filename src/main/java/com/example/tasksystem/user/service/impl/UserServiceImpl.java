package com.example.tasksystem.user.service.impl;

import com.example.tasksystem.user.dto.UpdateUserDto;
import com.example.tasksystem.user.dto.UpdateUserRoleDto;
import com.example.tasksystem.user.dto.UserResponseDto;
import com.example.tasksystem.user.mapper.UserMapper;
import com.example.tasksystem.user.model.Role;
import com.example.tasksystem.user.model.User;
import com.example.tasksystem.user.repository.UserRepository;
import com.example.tasksystem.user.service.UserService;
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
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Set<Role> roles = updateRoleDto.roles();
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Roles must not be empty");
        }

        user.setRoles(roles);
        final User updatedUser = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional()
    public UserResponseDto getMyProfile() {
        final User me = getCurrentUserEntity();
        return userMapper.toDto(me);
    }

    @Override
    @Transactional
    public UserResponseDto updateMyProfile(UpdateUserDto userUpdateDto) {
        final User me = getCurrentUserEntity();

        userMapper.updateFromDto(userUpdateDto, me);

        final User saved = userRepository.save(me);
        return userMapper.toDto(saved);
    }

    private User getCurrentUserEntity() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new EntityNotFoundException("Current user not authenticated");
        }

        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + auth.getName()));
    }

    @Override
    public Long getChatIdById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return user.getTelegramChatId();
    }

    @Override
    public void updateChatIdById(Long userId, Long chatId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setTelegramChatId(chatId);
        User saved = userRepository.save(user);

        userMapper.toDto(saved);
    }
}
