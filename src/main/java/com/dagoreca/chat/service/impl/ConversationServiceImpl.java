package com.dagoreca.chat.service.impl;


import com.dagoreca.chat.controller.error.BaseException;
import com.dagoreca.chat.domain.Conversation;
import com.dagoreca.chat.repository.ConversationRepository;
import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessageRequestDTO;
import com.dagoreca.chat.service.dto.MessagesDTO;
import com.dagoreca.chat.service.mapper.ConversationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {

    private final UserService userService;
    private final MongoTemplate mongoTemplate;

    private final ConversationMapper conversationMapper;

    private final ConversationRepository conversationRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    private final Logger logger = LoggerFactory.getLogger(ConversationService.class);

    public ConversationServiceImpl(UserService userService, MongoTemplate mongoTemplate, ConversationMapper conversationMapper, ConversationRepository conversationRepository,
                                   SequenceGeneratorService sequenceGeneratorService) {
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
        this.conversationMapper = conversationMapper;
        this.conversationRepository = conversationRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public ConversationDTO getConversationByUsername(String friendLogin) {
        String currentUser = userService.getCurrentUser().getLogin();
        Query query = getActualConversationQuery(friendLogin, currentUser);
        Conversation conversation = mongoTemplate.findOne(query, Conversation.class);
        int messagesSize = conversation.getMessages().size() - 1;
        conversation.getMessages().get(messagesSize).setEncodedFile(Base64.getEncoder().encodeToString(conversation.getMessages().get(messagesSize).getMessageFile().getContent().getData()));;
        return conversationMapper.toDto(conversation);
    }

    private Query getActualConversationQuery(String friendLogin, String currentUserLogin) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("conversationMembers").in(friendLogin), Criteria.where("conversationMembers").in(currentUserLogin));
        query.addCriteria(criteria);
        return query;
    }

    @Override
    public ConversationDTO getConversationById(Long conversationId) {
        logger.debug("Request to get conversation with id : {}", conversationId);
        Optional<Conversation> optionalConversationDTO = conversationRepository.findById(conversationId);
        if (optionalConversationDTO.isPresent()) {
            return conversationMapper.toDto(optionalConversationDTO.get());
        } else throw new BaseException("Conversation does not exist");
    }


    @Override
    public ConversationDTO getActualConversation(MessageRequestDTO messageRequestDTO) {
        logger.debug("Request to get actual conversation");
        return conversationMapper.toDto(conversationRepository
                .findById(messageRequestDTO.getConversationId()).orElseThrow(RuntimeException::new));
    }

    @Override
    public ConversationDTO save(ConversationDTO conversationDTO) {
        logger.debug("Request to save conversation : {}", conversationDTO);
        Conversation conversation = conversationRepository.save(conversationMapper.toEntity(conversationDTO));
        return conversationMapper.toDto(conversation);
    }

    @Override
    public ConversationDTO createConversation(List<String> usersList) {
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setConversationMembers(usersList);
        conversationDTO.setMessages(new ArrayList<>());
        conversationDTO.setId(sequenceGeneratorService.generateSequence(Conversation.SEQUENCE_NAME));
        Conversation conversation = conversationMapper.toEntity(conversationDTO);
        conversationRepository.save(conversation);
        return conversationMapper.toDto(conversation);
    }

    @Override
    public MessageRequestDTO handleMessage(MessageRequestDTO messageRequestDTO) {
        Instant actualDate = Instant.now();
        ConversationDTO actualConversation = updateConversation(messageRequestDTO, actualDate);
        updateMessageRequest(messageRequestDTO, actualDate, actualConversation);
        return messageRequestDTO;
    }

    private ConversationDTO updateConversation(MessageRequestDTO messageRequestDTO, Instant actualDate) {
        ConversationDTO actualConversation = getActualConversation(messageRequestDTO);
        actualConversation.addMessages(prepareMessage(messageRequestDTO, actualDate));
        return save(actualConversation);
    }

    private void updateMessageRequest(MessageRequestDTO messageRequestDTO, Instant actualDate, ConversationDTO conversation) {
        messageRequestDTO.setSendDate(actualDate);
        messageRequestDTO.setReceivers(conversation.getConversationMembers());
    }

    private MessagesDTO prepareMessage(MessageRequestDTO messageRequestDTO, Instant actualDate) {
        MessagesDTO messagesDTO = new MessagesDTO();
        messagesDTO.setSendDate(actualDate);
        messagesDTO.setContent(messageRequestDTO.getContent());
        messagesDTO.setAuthor(messageRequestDTO.getAuthor());
        return messagesDTO;
    }

}
