package com.example.tasksystem.notification.service.impl;

import com.example.tasksystem.notification.service.NotificationService;
import com.example.tasksystem.task.service.TaskService;
import com.example.tasksystem.telegram.service.TelegramBot;
import com.example.tasksystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final TelegramBot telegramBot;
    private final UserService userService;

    public void sendTaskAssignedNotification(Long userId,
                                             String taskName,
                                             String userName) {
        final String message = String.format("Hello, %s! You were assigned to the %s task.", userName, taskName);
        telegramBot.sendMessage(userService.getChatIdById(userId), message);
    }
}
