package com.dagoreca.chat.service.impl;


import com.dagoreca.chat.domain.Conversation;
import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.ConversationRepository;
import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.UserDTO;
import com.dagoreca.chat.service.mapper.ConversationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
