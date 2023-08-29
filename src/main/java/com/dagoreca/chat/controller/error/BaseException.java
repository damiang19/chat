package com.dagoreca.chat.controller.error;

public class BaseException extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }
}
