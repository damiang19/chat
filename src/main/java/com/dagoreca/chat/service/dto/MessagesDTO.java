package com.dagoreca.chat.service.dto;


import java.io.Serializable;
import java.util.Objects;

public class MessagesDTO implements Serializable {

    private Long id;
    private String content;

    private Long senderId;
    private String senderLogin;

    private Long recipientId;
    private String recipientLogin;

    public MessagesDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getRecipientLogin() {
        return recipientLogin;
    }

    public void setRecipientLogin(String recipientLogin) {
        this.recipientLogin = recipientLogin;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesDTO that = (MessagesDTO) o;
        return id.equals(that.id) && content.equals(that.content) && senderId.equals(that.senderId)
                && senderLogin.equals(that.senderLogin) && recipientId.equals(that.recipientId) && recipientLogin.equals(that.recipientLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, senderId, senderLogin, recipientId, recipientLogin);
    }

    @Override
    public String toString() {
        return "MessagesDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", senderId=" + senderId +
                ", senderLogin='" + senderLogin + '\'' +
                ", recipientId=" + recipientId +
                ", recipientLogin='" + recipientLogin + '\'' +
                '}';
    }
}
