package com.dagoreca.chat.service.mapper;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.service.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> toDto(List<User> user);
    List<User> toEntity(List<UserDTO> user);


    default User map(Long id){
        if(id == null){return null;}
        User user = new User();
        user.setId(id);
        return user;
    }
}
