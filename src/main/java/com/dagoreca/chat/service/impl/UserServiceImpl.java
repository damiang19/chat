package com.dagoreca.chat.service.impl;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.UserRepository;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserDTO;
import com.dagoreca.chat.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public User save(UserDTO userDTO){
        logger.info("Creating new user");
        userDTO.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(userMapper.toEntity(userDTO));
    }

    @Override
    public void deleteById(Long id) {
        try{
            userRepository.deleteById(id);
        } catch (Exception exception){

        }
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }
}
