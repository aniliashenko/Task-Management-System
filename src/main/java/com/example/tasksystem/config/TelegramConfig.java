package com.example.tasksystem.config;

import com.example.tasksystem.telegram.service.TelegramBot;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Profile("!test")
public class TelegramConfig {

    public TelegramConfig(TelegramBot bot) throws Exception {
        final TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
    }
}
