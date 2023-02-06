package com.dagoreca.chat.service.dto;

import java.time.Instant;
import java.util.Objects;


public class MessagesDTO {

    private String content;

    private Instant sendDate;

    private Boolean arrived;

    private String author;

    public MessagesDTO(){

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesDTO messages = (MessagesDTO) o;
        return Objects.equals(content, messages.content) && Objects.equals(sendDate, messages.sendDate)
                && Objects.equals(arrived, messages.arrived) && Objects.equals(author, messages.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, sendDate, arrived, author);
    }

    @Override
    public String toString() {
        return "Messages{" +
                ", content='" + content + '\'' +
                ", sendDate=" + sendDate +
                ", arrived=" + arrived +
                ", author=" + author +
                '}';
    }
}
