package com.dagoreca.chat.controller;

import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ConversationController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ConversationService conversationService;

    public ConversationController(SimpMessagingTemplate simpMessagingTemplate, ConversationService conversationService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.conversationService = conversationService;
    }

    @MessageMapping("/hello")
    public void send(SimpMessageHeaderAccessor sha, @Payload MessageRequestDTO messagesDTO) {
        conversationService.updateConversation(messagesDTO);
        simpMessagingTemplate.convertAndSendToUser(sha.getUser().getName(), "/topic/messages", messagesDTO.getContent());
    }
}
