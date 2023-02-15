package com.dagoreca.chat.service;

import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessageRequestDTO;

import java.util.List;

public interface ConversationService {
    ConversationDTO createConversation(List<String> userList);

    ConversationDTO updateConversation(MessageRequestDTO messageRequestDTO);
}
