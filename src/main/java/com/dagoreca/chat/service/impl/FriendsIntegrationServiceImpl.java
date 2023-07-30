package com.dagoreca.chat.service.impl;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.FriendsIntegrationService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserDTO;
import com.dagoreca.chat.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsIntegrationServiceImpl implements FriendsIntegrationService {

    private final Logger logger = LoggerFactory.getLogger(FriendsIntegrationService.class);
    private final ConversationService conversationService;
    private final MongoTemplate mongoTemplate;
    private final UserService userService;
    private final UserMapper userMapper;

    public FriendsIntegrationServiceImpl(ConversationService conversationService, MongoTemplate mongoTemplate, UserService userService, UserMapper userMapper) {
        this.conversationService = conversationService;
        this.mongoTemplate = mongoTemplate;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void sendFriendInvitation(String login) {
        String actuallyLoggedUser =  userService.getCurrentUser().getLogin();
        UserDTO invitationRecipient = userService.findOne(login);
        invitationRecipient.addFriendInvitations(actuallyLoggedUser);
        userService.updateUser(invitationRecipient);
    }

    @Override
    public void acceptInvitationToFriend(String login) {
        UserDTO actuallyLoggedUser =  userService.getCurrentUser();
        UserDTO invitingUser = userService.findOne(login);
        actuallyLoggedUser.getFriendInvitations().stream()
                .filter(user -> user.startsWith(login)).findFirst()
                .ifPresent(user -> {
                    actuallyLoggedUser.addFriends(user);
                    actuallyLoggedUser.getFriendInvitations().remove(user);
                    userService.updateUser(actuallyLoggedUser);
                    invitingUser.addFriends(actuallyLoggedUser.getLogin());
                    userService.updateUser(invitingUser);
                    conversationService.createConversation(List.of(actuallyLoggedUser.getLogin(),login));
                });
    }

    @Override
    public List<UserDTO> getFriendsList() {
        UserDTO currentUser =  userService.getCurrentUser();
        Query query = new Query();
        query.addCriteria(Criteria.where("login").in(currentUser.getFriends()));
        List<User> friends = mongoTemplate.find(query, User.class);
        return userMapper.toDto(friends);
    }
    @Override
    public List<UserDTO> getFriendInvitationsList() {
        UserDTO currentUser =  userService.getCurrentUser();
        Query query = new Query();
        query.addCriteria(Criteria.where("login").in(currentUser.getFriendInvitations()));
        List<User> friends = mongoTemplate.find(query, User.class);
        return userMapper.toDto(friends);
    }

}
