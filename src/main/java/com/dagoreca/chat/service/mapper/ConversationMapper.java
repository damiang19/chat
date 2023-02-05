package com.dagoreca.chat.service.mapper;

import com.dagoreca.chat.domain.Conversation;
import com.dagoreca.chat.service.dto.ConversationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ConversationMapper {
    ConversationDTO toDto(Conversation conversation);

    Conversation toEntity(ConversationDTO conversationDTO);


}
