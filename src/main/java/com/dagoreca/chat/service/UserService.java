package com.dagoreca.chat.service;


import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    User createNewUser(UserDTO user);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO updateAccountInformation(UserDTO userDTO);

    void deleteById(Long id);

    UserDTO findOne(Long id);

    UserDTO findOne(String login);

    UserDTO getCurrentUser();

    List<UserDTO> findAll();

    List<UserDTO> findAllByLogin(String login);


}
