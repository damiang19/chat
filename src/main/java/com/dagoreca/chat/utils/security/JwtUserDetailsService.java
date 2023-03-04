package com.dagoreca.chat.utils.security;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.UserRepository;
import com.dagoreca.chat.service.dto.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
      User user = userRepository.findByLogin(login).get();
//        List<GrantedAuthority> authoritiesList = new ArrayList<>() ;
//        authoritiesList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (user.getLogin().equals(login)) {
            return UserDetailsImpl.build(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + login);
        }
    }
}
