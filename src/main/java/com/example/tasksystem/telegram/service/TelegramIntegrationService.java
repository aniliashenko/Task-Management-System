package com.example.tasksystem.telegram.service;

import com.example.tasksystem.telegram.model.TelegramLinkToken;
import com.example.tasksystem.telegram.repository.TelegramLinkTokenRepository;
import com.example.tasksystem.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TelegramIntegrationService {
    private final TelegramLinkTokenRepository tokenRepository;
    private final UserService userService;

    @Transactional
    public void linkTelegramAccount(String token, Long chatId) {
        final TelegramLinkToken linkToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired token"));

        if (linkToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expired");
        }

        userService.updateChatIdById(linkToken.getUserId(), chatId);

        tokenRepository.deleteByToken(token);
    }
}
