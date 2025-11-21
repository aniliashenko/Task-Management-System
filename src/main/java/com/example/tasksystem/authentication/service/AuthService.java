package com.example.tasksystem.authentication.service;

import com.example.tasksystem.authentication.dto.AuthResponseDto;
import com.example.tasksystem.authentication.dto.LoginRequestDto;
import com.example.tasksystem.authentication.dto.RegisterRequestDto;
import com.example.tasksystem.authentication.jwt.JwtProvider;
import com.example.tasksystem.user.model.Role;
import com.example.tasksystem.user.model.User;
import com.example.tasksystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthResponseDto register(RegisterRequestDto requestDto) {
        if (userRepository.findByUsername(requestDto.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        final User user = new User();
        user.setUsername(requestDto.username());
        user.setEmail(requestDto.email());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setFirstName(requestDto.firstName());
        user.setLastName(requestDto.lastName());
        user.setRoles(Set.of(Role.USER));
        userRepository.save(user);

        final String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.username(),
                        requestDto.password()
                )
        );

        final String token = jwtProvider.generateToken(requestDto.username());
        return new AuthResponseDto(token);
    }
}
