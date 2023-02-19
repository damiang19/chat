package com.dagoreca.chat.service.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConversationDTO implements Serializable {

    private Long id ;
    private String conversationName;
    private List<String> conversationMembers;
    private List<MessagesDTO> messages;

    public ConversationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getConversationMembers() {
        return conversationMembers;
    }

    public void setConversationMembers(List<String> conversationMembers) {
        this.conversationMembers = conversationMembers;
    }

    public List<MessagesDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesDTO> messages) {
        this.messages = messages;
    }

    public void addConversationMembers(String user) {
        if (conversationMembers == null) {
            conversationMembers = new ArrayList<>();
        }
        conversationMembers.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationDTO that = (ConversationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(conversationMembers, that.conversationMembers) && Objects.equals(messages, that.messages);
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conversationMembers, messages);
    }

    @Override
    public String toString() {
        return "MessagesDTO{" +
                "id=" + id +
                ", conversationMembers=" + conversationMembers +
                ", messages=" + messages +
                ", conversationName=" + conversationName +
                '}';
    }
}
