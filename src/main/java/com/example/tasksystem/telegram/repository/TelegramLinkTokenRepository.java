package com.example.tasksystem.telegram.repository;

import com.example.tasksystem.telegram.model.TelegramLinkToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelegramLinkTokenRepository extends JpaRepository<TelegramLinkToken, Long> {
    Optional<TelegramLinkToken> findByToken(String token);

    void deleteByToken(String token);
}
