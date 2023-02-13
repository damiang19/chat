package com.dagoreca.chat.service.dto;


import com.dagoreca.chat.domain.User;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public class FriendInvitationsDTO {
    UserDTO inviting;
    @Field
    Boolean isAccepted = false;

    public FriendInvitationsDTO(UserDTO inviting) {
        this.inviting = inviting;
    }

    public UserDTO getInviting() {
        return inviting;
    }

    public void setInviting(UserDTO inviting) {
        this.inviting = inviting;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendInvitationsDTO that = (FriendInvitationsDTO) o;
        return Objects.equals(inviting, that.inviting) && Objects.equals(isAccepted, that.isAccepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inviting, isAccepted);
    }

    @Override
    public String toString() {
        return "FriendInvitationsDTO{" +
                "inviting=" + inviting +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
