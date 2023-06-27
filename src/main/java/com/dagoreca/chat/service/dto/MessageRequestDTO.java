package com.dagoreca.chat.service.dto;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class MessageRequestDTO {

    private Long conversationId;
    private String content;
    private String author;
    private Instant sendDate;

    private List<String> receivers;

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

    public Instant getSendDate() {
        return sendDate;
    }

    public void setSendDate(Instant sendDate) {
        this.sendDate = sendDate;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageRequestDTO that = (MessageRequestDTO) o;
        return Objects.equals(conversationId, that.conversationId) && Objects.equals(content, that.content) && Objects.equals(author, that.author) && Objects.equals(sendDate, that.sendDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, content, author, sendDate);
    }

    @Override
    public String toString() {
        return "MessageRequestDTO{" +
                "conversationId=" + conversationId +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
