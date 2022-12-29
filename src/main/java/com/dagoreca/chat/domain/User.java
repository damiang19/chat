package com.dagoreca.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "uzytkownik")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 25)
    @Column(length = 25, unique = true)
    private String login;

    @NotNull
    @JsonIgnore
    @Size(min = 8, max = 32)
    @Column(name = "password_hash", length = 32, nullable = false)
    private String password;


}
