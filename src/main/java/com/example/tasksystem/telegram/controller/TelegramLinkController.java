package com.example.tasksystem.telegram.controller;

import com.example.tasksystem.telegram.service.TelegramLinkService;
import com.example.tasksystem.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telegram")
@RequiredArgsConstructor
public class TelegramLinkController {
    private final TelegramLinkService linkService;

    @PostMapping("/generate-link")
    public ResponseEntity<String> generateTelegramLink(@AuthenticationPrincipal UserDetails user) {
        String token = linkService.generateLinkToken(((User) user).getId());
        String telegramLink = "https://t.me/taskSystemAzuraBot?start=" + token;
        return ResponseEntity.ok(telegramLink);
    }
}
