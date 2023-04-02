package com.example.todolistrest.utils;
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
