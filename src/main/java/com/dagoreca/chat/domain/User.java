package com.dagoreca.chat.domain;

import com.dagoreca.chat.domain.enums.Roles;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.security.Principal;
import java.util.*;


@Document("uzytkownik")
public class User implements Principal {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;
    private String email;
    @Indexed(unique = true)
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String firstName;
    private String lastName;
    private List<String> friends;
    private List<String> friendInvitations;

    private Set<Roles> roles = new HashSet<>();

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getName() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getFriendInvitations() {
        return friendInvitations;
    }

    public void setFriendInvitations(List<String> friendInvitations) {
        this.friendInvitations = friendInvitations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addFriendInvitations(String login) {
        if (friendInvitations == null) {
            friendInvitations = new ArrayList<>();
        }
        friendInvitations.add(login);
    }

    public void addFriends(String login) {
        if (friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(login);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(friends, user.friends) && Objects.equals(friendInvitations, user.friendInvitations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, friends, friendInvitations);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friends=" + friends +
                ", friendInvitations=" + friendInvitations +
                '}';
    }
}
