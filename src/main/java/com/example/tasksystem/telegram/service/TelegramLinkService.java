package com.example.tasksystem.telegram.service;

import com.example.tasksystem.telegram.model.TelegramLinkToken;
import com.example.tasksystem.telegram.repository.TelegramLinkTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelegramLinkService {
    private final TelegramLinkTokenRepository tokenRepository;

    public String generateLinkToken(Long userId) {
        String token = UUID.randomUUID().toString();
        TelegramLinkToken linkToken = new TelegramLinkToken();
        linkToken.setUserId(userId);
        linkToken.setToken(token);
        linkToken.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        tokenRepository.save(linkToken);
        return token;
    }
}
