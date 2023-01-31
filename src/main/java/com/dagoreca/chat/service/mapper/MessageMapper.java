package com.dagoreca.chat.service.mapper;

import com.dagoreca.chat.service.dto.MessagesDTO;
import com.dagoreca.chat.domain.Messages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MessageMapper {
    @Mapping(source="recipient.login", target="recipientLogin")
    @Mapping(source="sender.login", target="senderLogin")
    MessagesDTO toDto(Messages messages);

    @Mapping(source="recipientId", target="recipient")
    @Mapping(source = "senderId", target = "sender")
    Messages toEntity(MessagesDTO messagesDTO);

}
