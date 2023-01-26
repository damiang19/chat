package com.dagoreca.chat.service;


import com.dagoreca.chat.domain.User;

public interface UserService {

    User save(User user);

    void deleteById(Long id);


}
