package com.dagoreca.chat.controller;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.ConversationService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final MongoTemplate mongoTemplate;
    private final ConversationService conversationService;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserController(UserService userService, ConversationService conversationService, MongoTemplate mongoTemplate) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDTO user) {
        logger.debug("REST request to register new User : {}", user);
        User newUser = userService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<UserDTO> updateAccountDetails(@Valid @RequestBody UserDTO user) {
        logger.debug("REST request to update User account details: {}", user);
        UserDTO newUser = userService.updateAccountInformation(user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> findUsers() {
        logger.debug("REST request to get all users");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        logger.debug("REST request to get all users");
        UserDTO userDTO = userService.getCurrentUser();
        return ResponseEntity.ok(userDTO);
    }


    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.debug("REST request to delete user with id: {}", id);
        try {
            userService.deleteById(id);
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
