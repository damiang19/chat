package com.dagoreca.chat.service.dto;

import java.time.Instant;
import java.util.Objects;


public class MessagesDTO {

    private String content;

    private Instant sendDate;

    private Boolean arrived;

    private String receiver;

    public MessagesDTO(){

    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public void setSendDate(Instant sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getArrived() {
        return arrived;
    }

    public void setArrived(Boolean arrived) {
        this.arrived = arrived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesDTO that = (MessagesDTO) o;
        return Objects.equals(content, that.content) && Objects.equals(sendDate, that.sendDate) && Objects.equals(arrived, that.arrived) && Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, sendDate, arrived, receiver);
    }

    @Override
    public String toString() {
        return "MessagesDTO{" +
                "content='" + content + '\'' +
                ", sendDate=" + sendDate +
                ", arrived=" + arrived +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
