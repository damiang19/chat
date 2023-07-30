package com.dagoreca.chat.utils.exceptions;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
    }

    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
