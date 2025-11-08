package com.example.tasksystem.notification.service;

public interface NotificationService {

    void sendTaskAssignedNotification(Long userId, String taskName, String userName);
}
