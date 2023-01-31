package com.dagoreca.chat.controller;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.UserQueryService;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserCriteria;
import com.dagoreca.chat.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

         throw new RuntimeException();
    }

    @GetMapping(value="/users")
    public ResponseEntity<List<UserDTO>> findUsers(@RequestParam UserCriteria userCriteria){
        return ResponseEntity.ok(userQueryService.findByCriteria(userCriteria));
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
