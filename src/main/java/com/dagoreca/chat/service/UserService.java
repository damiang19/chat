package com.dagoreca.chat.service;


import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createNewUser(UserDTO user);

    UserDTO updateUser(UserDTO userDTO);

    void deleteById(Long id);

    Optional<UserDTO> findOne(Long id);

    Optional<UserDTO> findOne(String login);

    UserDTO getCurrentUser();

    List<UserDTO> findAll();

    List<UserDTO> findAllByLogin(String login);


}
