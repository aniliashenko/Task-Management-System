package com.example.tasksystem.security;

import com.example.tasksystem.dto.auth.AuthResponseDto;
import com.example.tasksystem.dto.auth.LoginRequestDto;
import com.example.tasksystem.dto.auth.RegisterRequestDto;
import com.example.tasksystem.model.Role;
import com.example.tasksystem.model.User;
import com.example.tasksystem.repository.UserRepository;
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

        User user = new User();
        user.setUsername(requestDto.username());
        user.setEmail(requestDto.email());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setFirstName(requestDto.firstName());
        user.setLastName(requestDto.lastName());
        user.setRoles(Set.of(Role.USER, Role.ADMIN));
        userRepository.save(user);

        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.username(),
                        requestDto.password()
                )
        );

        String token = jwtProvider.generateToken(requestDto.username());
        return new AuthResponseDto(token);
    }
}
