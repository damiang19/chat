package com.dagoreca.chat.service;

import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import com.dagoreca.chat.service.dto.MessagesDTO;

import java.util.List;

public interface ConversationService {
    ConversationDTO createConversation(List<String> userList);

    MessageRequestDTO updateConversation(MessageRequestDTO messageRequestDTO);

    ConversationDTO getConversation(String login);
}
