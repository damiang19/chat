package com.dagoreca.chat.service.impl;


import com.dagoreca.chat.domain.Conversation;
import com.dagoreca.chat.repository.ConversationRepository;
import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import com.dagoreca.chat.service.dto.MessagesDTO;
import com.dagoreca.chat.service.mapper.ConversationMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {

    private final UserService userService;
    private final MongoTemplate mongoTemplate;

    private ConversationMapper conversationMapper;

    private final ConversationRepository conversationRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    public ConversationServiceImpl(UserService userService, MongoTemplate mongoTemplate, ConversationMapper conversationMapper, ConversationRepository conversationRepository,
                                   SequenceGeneratorService sequenceGeneratorService) {
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
        this.conversationMapper = conversationMapper;
        this.conversationRepository = conversationRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public ConversationDTO createConversation(List<String> usersList){
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setConversationMembers(usersList);
        conversationDTO.setMessages(new ArrayList<>());
        conversationDTO.setId(sequenceGeneratorService.generateSequence(Conversation.SEQUENCE_NAME));
        Conversation conversation = conversationMapper.toEntity(conversationDTO);
        conversationRepository.save(conversation);
        return conversationMapper.toDto(conversation);
    }
    @Override
    public MessageRequestDTO updateConversation(MessageRequestDTO messageRequestDTO){
       Conversation actualConversation = conversationRepository.findById(messageRequestDTO.getConversationId()).orElseThrow(RuntimeException::new);
       Instant actualDate = Instant.now();
       messageRequestDTO.setSendDate(actualDate);
       messageRequestDTO.setReceivers(actualConversation.getConversationMembers());
       MessagesDTO messagesDTO = new MessagesDTO();
        messagesDTO.setSendDate(actualDate);
        messagesDTO.setContent(messageRequestDTO.getContent());
        messagesDTO.setAuthor(messageRequestDTO.getAuthor());
        actualConversation.addMessages(messagesDTO);
       conversationRepository.save(actualConversation);
       return messageRequestDTO;
    }

    @Override
    public ConversationDTO getConversation(String friendLogin) {
        String currentUser =  userService.getCurrentUser().getLogin();
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("conversationMembers").in(friendLogin),Criteria.where("conversationMembers").in(currentUser));
        query.addCriteria(criteria);
        Query f = Query.query(Criteria.where("friends").elemMatch(
                Criteria.where("$elemMatch").is(Arrays.asList("John", "Doe"))
        ));
        return conversationMapper.toDto(mongoTemplate.findOne(query, Conversation.class));
    }

}
