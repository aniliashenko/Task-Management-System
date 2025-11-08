package com.example.tasksystem.telegram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String botUsername;

    private final TelegramIntegrationService telegramIntegrationService;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (message.startsWith("/start")) {
                String[] parts = message.split(" ");
                if (parts.length > 1) {
                    String token = parts[1];
                    telegramIntegrationService.linkTelegramAccount(token, chatId);
                } else {
                    sendMessage(chatId, "Hello! To link your account, go to the app and get the link.");
                }
            }
        }
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId.toString(), text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
