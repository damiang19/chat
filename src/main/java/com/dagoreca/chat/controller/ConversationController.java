package com.dagoreca.chat.controller;

import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConversationController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ConversationService conversationService;

    private final SimpUserRegistry simpUserRegistry;


    public ConversationController(SimpMessagingTemplate simpMessagingTemplate, ConversationService conversationService, SimpUserRegistry simpUserRegistry) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.conversationService = conversationService;
        this.simpUserRegistry = simpUserRegistry;
    }

    @MessageMapping("/hello")
    public void send(SimpMessageHeaderAccessor sha, @Payload MessageRequestDTO messageRequestDTO) {
        messageRequestDTO = conversationService.updateConversation(messageRequestDTO);
        for(String userLogin : messageRequestDTO.getReceivers()){
            simpMessagingTemplate.convertAndSendToUser(userLogin, "/topic/messages", messageRequestDTO);
        }
    }

    @GetMapping("/ws/users")
    public List<String> connectedEquipments() {
        return this.simpUserRegistry
                .getUsers()
                .stream()
                .map(SimpUser::getName)
                .collect(Collectors.toList());
    }
}
