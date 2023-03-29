package com.dagoreca.chat.controller;


import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.FriendsIntegrationService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.ConversationDTO;
import com.dagoreca.chat.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendsIntegrationController {

    private final UserService userService;
    private final ConversationService conversationService;
    private final FriendsIntegrationService friendsIntegrationService;
    private Logger logger = LoggerFactory.getLogger(FriendsIntegrationController.class);

    public FriendsIntegrationController(UserService userService, ConversationService conversationService, FriendsIntegrationService friendsIntegrationService) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.friendsIntegrationService = friendsIntegrationService;
    }

    @PutMapping(value = "/accept-friend-invitation")
    public ResponseEntity<Void> acceptInvitationToFriendList(@RequestParam String login){
        friendsIntegrationService.acceptInvitationToFriend(login);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/send-friend-invitation")
    public ResponseEntity<Void> sendInvitationToFriendList(@RequestParam String login){
        friendsIntegrationService.sendFriendInvitation(login);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/friends")
    public ResponseEntity<List<UserDTO>> getFriends() {
        logger.debug("REST request to get friends list ");
        List<UserDTO> friends = friendsIntegrationService.getFriendsList();
        return ResponseEntity.ok().body(friends);
    }

    @GetMapping(value = "/friends-invitations")
    public ResponseEntity<List<UserDTO>> getFriendInvitations() {
        logger.debug("REST request to get friends list ");
        List<UserDTO> friends = friendsIntegrationService.getFriendInvitationsList();
        return ResponseEntity.ok().body(friends);
    }

    @GetMapping(value = "/friend/conversation")
    public ResponseEntity<ConversationDTO> getConversation(@RequestParam String friendLogin) {
        ConversationDTO conversationDTO = conversationService.getConversation(friendLogin);
        return ResponseEntity.ok().body(conversationDTO);
    }

    @GetMapping(value = "/search-for-friends")
    public ResponseEntity<List<UserDTO>> findUsers(@RequestParam String login) {
        logger.debug("REST request to find users");
        List<UserDTO> userDTOList =  userService.findAllByLogin(login);
        return ResponseEntity.ok().body(userDTOList);
    }
}
