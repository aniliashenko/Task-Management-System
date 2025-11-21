package com.example.tasksystem.notification.service;

public interface NotificationService {

    void sendTaskAssignedNotification(Long userId, String taskName, String userName);

    void sendTaskUpdatedNotification(Long userId, String userName);

    void sendTaskDeletedNotification(Long userId, String taskName, String userName);
}
