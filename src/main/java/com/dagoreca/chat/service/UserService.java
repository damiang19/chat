package com.dagoreca.chat.service;


import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    User save(UserDTO user);

    void deleteById(Long id);

    List<UserDTO> findAll();


}
