package com.dagoreca.chat.controller;

import com.dagoreca.chat.service.dto.jwt.JwtRequestDTO;
import com.dagoreca.chat.service.dto.jwt.JwtResponseDTO;
import com.dagoreca.chat.utils.security.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil1, AuthenticationManager authenticationManager1) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil1;
        this.authenticationManager = authenticationManager1;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDTO authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponseDTO(jwtTokenUtil.generateToken(userDetails)));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
