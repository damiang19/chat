package com.dagoreca.chat.service.impl;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.domain.enums.Roles;
import com.dagoreca.chat.repository.UserRepository;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserDTO;
import com.dagoreca.chat.service.mapper.UserMapper;
import com.dagoreca.chat.utils.exceptions.UserNotFoundException;
import com.dagoreca.chat.utils.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, SequenceGeneratorService sequenceGeneratorService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public User createNewUser(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByLogin(userDTO.getLogin());
        if(optionalUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Podany login jest już zajęty");
        }
        logger.info("Creating new user with login: {}", userDTO.getLogin());
        userDTO.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.addRoles(Roles.ROLE_USER.getValue());
        userDTO.setFriends(new ArrayList<>());
        userDTO.setFriendInvitations(new ArrayList<>());
        return userRepository.save(userMapper.toEntity(userDTO));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        logger.info("Updating user with login: {}",userDTO.getLogin());
        User user =  userMapper.toEntity(userDTO);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDTO updateAccountInformation(UserDTO userDTO){
       UserDTO userToUpdate = findOne(userDTO.getLogin());
       userToUpdate.setFirstName(userDTO.getFirstName());
       userToUpdate.setLastName(userDTO.getLastName());
       if(!Objects.equals(userDTO.getPassword(), userToUpdate.getPassword())){
           userToUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
       }
       User user =  userMapper.toEntity(userToUpdate);
       userRepository.save(user);
       return userDTO;
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id:" + id + "has not been found");
        }
    }

    @Override
    public UserDTO findOne(Long id) {
        logger.debug("Request to get User with id : {}", id);
        Optional<User> userDTO = userRepository.findById(id);
        if (userDTO.isPresent()) {
            return userMapper.toDto(userDTO.get());
        } else {
            throw new UserNotFoundException("User with id:" + id + "has not been found");
        }
    }

    @Override
    public UserDTO findOne(String login) {
        logger.debug("Request to get User with login : {}", login);
        Optional<User> userDTO = userRepository.findByLogin(login);
        if (userDTO.isPresent()) {
            return userMapper.toDto(userDTO.get());
        } else {
            throw new UserNotFoundException("User with login:" + login + "has not been found");
        }
    }

    @Override
    public UserDTO getCurrentUser() {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin.isPresent()) {
            return findOne(currentUserLogin.get());
        } else {
            throw new UserNotFoundException("You are not logged");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public List<UserDTO> findAllByLogin(String login) {
        logger.debug("Request to find users with login starting with:{}", login);
        List<User> userList =  userRepository.findAllByLoginStartingWith(login);
        return userMapper.toDto(userList);
    }
}
