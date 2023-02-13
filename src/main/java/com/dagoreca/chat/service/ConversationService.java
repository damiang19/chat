package com.dagoreca.chat.service;

import com.dagoreca.chat.service.dto.ConversationDTO;

import java.util.List;

public interface ConversationService {
    ConversationDTO createConversation(List<String> userList);
}
