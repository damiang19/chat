package com.dagoreca.chat.utils.security;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
      User user = userRepository.findByLogin(login);
        List<GrantedAuthority> authoritiesList = new ArrayList<>() ;
        authoritiesList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (user.getLogin().equals(login)) {
            return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                    authoritiesList);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + login);
        }
    }
}
