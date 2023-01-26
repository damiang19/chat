package com.dagoreca.chat.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wiadomosci")
public class Messages {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tresc_wiadomosci")
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nadawca")
    private User sender;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "odbiorca")
    private User recipient;

    public Messages(){

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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }
}
