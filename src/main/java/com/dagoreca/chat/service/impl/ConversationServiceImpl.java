package com.dagoreca.chat.service.impl;


import com.dagoreca.chat.domain.Conversation;
import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.ConversationRepository;
import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import com.dagoreca.chat.service.dto.MessagesDTO;
import com.dagoreca.chat.service.dto.UserDTO;
import com.dagoreca.chat.service.mapper.ConversationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {

    private ConversationMapper conversationMapper;

    private final ConversationRepository conversationRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    public ConversationServiceImpl(ConversationMapper conversationMapper, ConversationRepository conversationRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.conversationMapper = conversationMapper;
        this.conversationRepository = conversationRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public ConversationDTO createConversation(List<String> usersList){
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setConversationMembers(usersList);
        conversationDTO.setId(sequenceGeneratorService.generateSequence(Conversation.SEQUENCE_NAME));
        Conversation conversation = conversationMapper.toEntity(conversationDTO);
        conversationRepository.save(conversation);
        return conversationMapper.toDto(conversation);
    }
    @Override
    public ConversationDTO updateConversation(MessageRequestDTO messageRequestDTO){
       Conversation actualConversation = conversationRepository.findById(messageRequestDTO.getConversationId()).orElseThrow(RuntimeException::new);
        MessagesDTO messagesDTO = new MessagesDTO();
        messagesDTO.setSendDate(Instant.now());
        messagesDTO.setContent(messageRequestDTO.getContent());
       actualConversation.addMessages(messagesDTO);
       conversationRepository.save(actualConversation);
       return conversationMapper.toDto(actualConversation);
    }

}
