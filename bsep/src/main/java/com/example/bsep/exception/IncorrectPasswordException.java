package com.example.bsep.exception;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(String message){
        super(message);
    }
}
