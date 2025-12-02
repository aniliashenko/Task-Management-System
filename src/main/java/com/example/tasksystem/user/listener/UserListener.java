package com.example.tasksystem.user.listener;

import com.example.tasksystem.telegram.event.UserLoginEvent;
import com.example.tasksystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserListener {

    private final UserService userService;

    @EventListener
    public void onUserLogin(UserLoginEvent event) {
    }
}
