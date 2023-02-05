package com.dagoreca.chat.domain;

import com.dagoreca.chat.service.dto.MessagesDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Document("conversations")
public class Conversation {

    @Id
    private Long id ;

    private List<User> conversationMembers;

    private List<MessagesDTO> messages;

    public Conversation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getConversationMembers() {
        return conversationMembers;
    }

    public void setConversationMembers(List<User> conversationMembers) {
        this.conversationMembers = conversationMembers;
    }

    public List<MessagesDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesDTO> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(id, that.id) && Objects.equals(conversationMembers, that.conversationMembers) && Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conversationMembers, messages);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", conversationMembers=" + conversationMembers +
                ", messages=" + messages +
                '}';
    }
}
