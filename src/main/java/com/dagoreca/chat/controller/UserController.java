package com.dagoreca.chat.controller;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.UserQueryService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private final UserQueryService userQueryService;

    public UserController(UserService userService, UserQueryService userQueryService) {
        this.userService = userService;
        this.userQueryService = userQueryService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value="/users")
    public ResponseEntity<List<User>> findUsers(UserCriteria userCriteria){
        return ResponseEntity.ok(userQueryService.findByCriteria(userCriteria));
    }

}
