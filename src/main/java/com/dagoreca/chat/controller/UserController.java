package com.dagoreca.chat.controller;

import com.dagoreca.chat.domain.Conversation;
import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final MongoTemplate mongoTemplate;
    private final ConversationService conversationService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserController(UserService userService, ConversationService conversationService, MongoTemplate mongoTemplate) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDTO user) {
        logger.debug("REST request to register new User : {}",user);
        User newUser = userService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<User> updateAccountDetails(@Valid @RequestBody UserDTO user) {
        logger.debug("REST request to update User account details: {}",user);
        User newUser = userService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @GetMapping(value="/users")
    public ResponseEntity<List<UserDTO>> findUsers(){
        logger.debug("REST request to get all users");
        return ResponseEntity.ok(userService.findAll());
    }


    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        logger.debug("REST request to delete user with id: {}",id);
        try{
            userService.deleteById(id);
           } catch (Exception exception){
            return ResponseEntity.notFound().build();
            }
        return ResponseEntity.noContent().build();
    }

    // TODO : przeniesc interakcje pomiedzy znajomymi to osobnego kontrolera
        @GetMapping(value = "/friends")
        public ResponseEntity<List<User>> getFriends() {
            logger.debug("REST request to get friends list ");
            UserDTO currentUser =  userService.getCurrentUser();
            Query query = new Query();
            query.addCriteria(Criteria.where("login").in(currentUser.getFriends()));
            List<User> friends = mongoTemplate.find(query, User.class);
            return ResponseEntity.ok().body(friends);
        }


    @GetMapping(value = "/friend/conversation")
    public ResponseEntity<Conversation> getConversation(@RequestParam String friendLogin) {
        UserDTO currentUser =  userService.getCurrentUser();
        Query query = new Query();
        query.addCriteria(Criteria.where("conversationMembers").in(currentUser,friendLogin));
        Conversation friends = mongoTemplate.findOne(query, Conversation.class);
        return ResponseEntity.ok().body(friends);
    }

    @GetMapping(value = "/search-for-friends")
    public ResponseEntity<List<UserDTO>> findUsers(@RequestParam String login) {
        logger.debug("REST request to find users");
        List<UserDTO> userDTOList =  userService.findAllByLogin(login);
        return ResponseEntity.ok().body(userDTOList);
    }


    @PutMapping(value = "/accept-friend-invitation")
    public ResponseEntity<Void> acceptInvitationToFriendList(@RequestParam String login){
        UserDTO currentUser =  userService.getCurrentUser();
        currentUser.getFriendInvitations().stream()
                .filter(user -> user.startsWith(login)).findFirst()
                .ifPresent(user -> {
                    currentUser.addFriends(user);
                    currentUser.getFriendInvitations().remove(user);
                    userService.updateUser(currentUser);
                    conversationService.createConversation(List.of(currentUser.getLogin(),login));
                });
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/send-friend-invitation")
    public ResponseEntity<Void> sendInvitationToFriendList(@RequestParam String login){
        UserDTO userDTO =  userService.getCurrentUser();
        try{
            userService.findOne(login).ifPresent(receiver ->{
               receiver.addFriendInvitations(userDTO.getLogin());
               userService.updateUser(receiver);
            });
            return ResponseEntity.noContent().build();
        }catch(Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
