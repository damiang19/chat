package com.dagoreca.chat.service.dto;

public class MessageRequestDTO {

    private Long conversationId;
    private String content;
    private String receiver;

    public MessageRequestDTO() {
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "MessageRequestDTO{" +
                "conversationId=" + conversationId +
                ", content='" + content + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
