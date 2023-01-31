package com.dagoreca.chat.controller.jms;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsMessagesReceiverController {
    private final RabbitTemplate rabbitTemplate;

    public JmsMessagesReceiverController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/getMessage")
    public String receiveJmsMessage(){
        return  rabbitTemplate.receiveAndConvert("messages.queue").toString();
    }
}
