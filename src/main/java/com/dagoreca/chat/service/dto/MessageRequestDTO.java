package com.dagoreca.chat.service.dto;

public class MessageRequestDTO {

    private Long conversationId;
    private String content;
    private String author;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "MessageRequestDTO{" +
                "conversationId=" + conversationId +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
