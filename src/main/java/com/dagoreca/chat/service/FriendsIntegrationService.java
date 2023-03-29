package com.dagoreca.chat.service;

import com.dagoreca.chat.domain.Conversation;
import com.dagoreca.chat.service.dto.UserDTO;

import java.util.List;

public interface FriendsIntegrationService {
    void sendFriendInvitation(String login);
    void acceptInvitationToFriend(String login);
    List<UserDTO> getFriendsList();
     List<UserDTO> getFriendInvitationsList();
}
