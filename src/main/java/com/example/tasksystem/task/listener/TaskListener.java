package com.example.tasksystem.task.listener;

import com.example.tasksystem.notification.service.NotificationService;
import com.example.tasksystem.task.event.TaskAssignedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskListener {

    private final NotificationService notificationService;

    @EventListener
    public void onTaskAssigned(TaskAssignedEvent event) {
        notificationService.sendTaskAssignedNotification(
                event.userId(),
                event.taskName(),
                event.username());
    }
}
