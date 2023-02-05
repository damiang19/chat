package com.dagoreca.chat.controller.jms;

import com.dagoreca.chat.service.JmsMessagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsMessagesController {
    private final JmsMessagingService jmsMessagingService;
    public JmsMessagesController(JmsMessagingService jmsMessagingService) {
        this.jmsMessagingService = jmsMessagingService;
    }

    @GetMapping("/convertAndSend/message")
    public String convertAndSendMessage(){
        jmsMessagingService.sendMessage();
        return "bla bla";
    }
}
