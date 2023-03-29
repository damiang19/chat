package com.dagoreca.chat.service.dto;


import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.domain.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;

public class UserDTO implements Serializable {

    private Long id;

    private String login;
    private String password;

    private String firstName;

    private String lastName;

    private List<String> friends;

    private List<String> friendInvitations;

    private Set<Roles> roles = new HashSet<>();


    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void addRoles(String role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(Roles.valueOf(role));
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public void setFriendInvitations(List<String> friendInvitations) {
        this.friendInvitations = friendInvitations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(login, userDTO.login) && Objects.equals(password, userDTO.password) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(friends, userDTO.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, friends, friendInvitations);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friends=" + friends +
                '}';
    }
}
