package com.dagoreca.chat.service.dto;

import java.time.Instant;
import java.util.List;
import java.util.Objects;


public class MessagesDTO {

    private String content;

    private Instant sendDate;

    private Instant dateOfReading;

    private String author;

    private List<String> whoReadTheMessage;

    public MessagesDTO(){

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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


    public Instant getDateOfReading() {
        return dateOfReading;
    }

    public void setDateOfReading(Instant dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    public List<String> getWhoReadTheMessage() {
        return whoReadTheMessage;
    }

    public void setWhoReadTheMessage(List<String> whoReadTheMessage) {
        this.whoReadTheMessage = whoReadTheMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesDTO that = (MessagesDTO) o;
        return Objects.equals(content, that.content) && Objects.equals(sendDate, that.sendDate) && Objects.equals(dateOfReading, that.dateOfReading) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, sendDate, dateOfReading, author);
    }

    @Override
    public String toString() {
        return "MessagesDTO{" +
                "content='" + content + '\'' +
                ", sendDate=" + sendDate +
                ", dateOfReading=" + dateOfReading +
                ", author='" + author + '\'' +
                '}';
    }
}
