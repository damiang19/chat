package com.dagoreca.chat.domain;

import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.MessagesDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document("conversations")
public class Conversation {

    @Transient
    public static final String SEQUENCE_NAME = "conversation_sequence";

    @Id
    private Long id ;

    private List<String> conversationMembers;

    private List<MessagesDTO> messages;

    private Boolean isGroupConversation;


    public Conversation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MessagesDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesDTO> messages) {
        this.messages = messages;
    }

    public List<String> getConversationMembers() {
        return conversationMembers;
    }

    public void setConversationMembers(List<String> conversationMembers) {
        this.conversationMembers = conversationMembers;
    }

    public Boolean getGroupConversation() {
        return isGroupConversation;
    }

    public void setGroupConversation(Boolean groupConversation) {
        isGroupConversation = groupConversation;
    }

    public void addConversationMembers(String user) {
        if (conversationMembers == null) {
            conversationMembers = new ArrayList<>();
        }
        conversationMembers.add(user);
    }

    public void addMessages(MessagesDTO messagesDTO) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(messagesDTO);
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
