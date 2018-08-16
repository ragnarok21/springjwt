package com.jwt.javawebtoken.exception;

public class UsernameException extends RuntimeException {

    private String message;

    public UsernameException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
