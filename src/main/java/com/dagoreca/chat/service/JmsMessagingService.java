package com.dagoreca.chat.service;

import com.dagoreca.chat.domain.Messages;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.amqp.support.converter.MessageConverter;

@Service
public class JmsMessagingService {
    private final RabbitTemplate rabbitTemplate;

    public JmsMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(){
        Messages content = new Messages();
        content.setContent("fifi");
        rabbitTemplate.convertAndSend("","messages.queue", "content");
    }
}
