package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(String message, HttpStatus accepted) {
        super("Room Not Found");
    }
}
