package com.dagoreca.chat.service;

import com.dagoreca.chat.service.dto.MessagesDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsMessagingService {
    private final RabbitTemplate rabbitTemplate;

    public JmsMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(){
        MessagesDTO content = new MessagesDTO();
        content.setContent("fifi");
        rabbitTemplate.convertAndSend("","messages.queue", "content");
    }
}
