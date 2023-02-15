package com.dagoreca.chat.controller;

import com.dagoreca.chat.service.dto.MessagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ConversationController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public void send(SimpMessageHeaderAccessor sha, @Payload MessagesDTO username) {
        String message = "Hello from " + sha.getUser().getName();
        simpMessagingTemplate.convertAndSendToUser(username, "/topic/messages", message);
    }
}
