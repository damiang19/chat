package com.dagoreca.chat.controller;

import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.FileService;
import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessageFileDTO;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConversationController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ConversationService conversationService;
    private final SimpUserRegistry simpUserRegistry;
    private final FileService fileService;


    public ConversationController(SimpMessagingTemplate simpMessagingTemplate, ConversationService conversationService, SimpUserRegistry simpUserRegistry, FileService fileService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.conversationService = conversationService;
        this.simpUserRegistry = simpUserRegistry;
        this.fileService = fileService;
    }

    @MessageMapping("/message-broker")
    public void send(SimpMessageHeaderAccessor sha, @Payload MessageRequestDTO messageRequestDTO) {
        messageRequestDTO = conversationService.handleMessage(messageRequestDTO);
        for (String userLogin : messageRequestDTO.getReceivers()) {
            simpMessagingTemplate.convertAndSendToUser(userLogin, "/topic/messages", messageRequestDTO);
        }
    }

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("file") MultipartFile file, @RequestParam Long conversationIdentity) throws IOException {
        MessageFileDTO messageFileDTO = fileService.addFile(file);
        ConversationDTO conversationDTO = conversationService.getConversationById(conversationIdentity);
        conversationDTO.getMessages().get(conversationDTO.getMessages().size() - 1).setMessageFile(messageFileDTO);
        conversationService.save(conversationDTO);
        return null;
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
