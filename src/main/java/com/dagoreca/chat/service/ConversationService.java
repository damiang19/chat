package com.dagoreca.chat.service;

import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import com.dagoreca.chat.service.dto.MessagesDTO;

import java.util.List;

public interface ConversationService {
    ConversationDTO createConversation(List<String> userList);

    MessageRequestDTO handleMessage(MessageRequestDTO messageRequestDTO);

    ConversationDTO getConversationByUsername(String login);

    ConversationDTO getActualConversation(MessageRequestDTO messageRequestDTO);

    ConversationDTO save(ConversationDTO conversationDTO);
}
