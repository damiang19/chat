package com.dagoreca.chat.service.dto;

public class JwtResponseDTO {
    private final String jwttoken;

    public JwtResponseDTO(String jwttoken) {
        this.jwttoken = jwttoken;
    }
    public String getToken() {
        return this.jwttoken;
    }
}
