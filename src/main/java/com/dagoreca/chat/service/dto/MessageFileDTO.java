package com.dagoreca.chat.service.dto;

import org.bson.types.Binary;

import java.util.Objects;

public class MessageFileDTO {

    private String title;
    private Binary content;

    public MessageFileDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageFileDTO that = (MessageFileDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }

    @Override
    public String toString() {
        return "MessageFileDTO{" +
                "title='" + title + '\'' +
                ", content=" + content +
                '}';
    }
}
