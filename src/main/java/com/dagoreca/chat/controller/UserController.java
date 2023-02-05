package com.dagoreca.chat.controller;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDTO user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @GetMapping(value="/users")
    public ResponseEntity<List<UserDTO>> findUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try{
            userService.deleteById(id);
           } catch (Exception exception){
            return ResponseEntity.notFound().build();
            }
        return ResponseEntity.noContent().build();
    }

}
