package com.jwt.javawebtoken.exception;

public class TokenErrorException extends RuntimeException {

    private String message;

    public TokenErrorException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}