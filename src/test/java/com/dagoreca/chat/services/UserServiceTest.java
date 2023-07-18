package com.dagoreca.chat.services;

import com.dagoreca.chat.SuperChatApplication;
import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.UserRepository;
import com.dagoreca.chat.service.UserService;
import com.dagoreca.chat.service.dto.UserDTO;
import com.dagoreca.chat.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuperChatApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserMapper userMapper;

    @Test
    public void testUpdateUser(){
        //GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("admin");
        userDTO.setPassword("admin");

        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");

        //WHEN
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        userService.updateUser(userDTO);

        //THEN
        verify(userMapper, times(1)).toEntity(userDTO);
        verify(userRepository, times(1)).save(user);

    }

    @Test
    public void testDeleteUser(){

        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteById(1L);

        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);

    }


}
