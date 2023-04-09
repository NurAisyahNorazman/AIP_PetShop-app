package com.assignment.pet.exceptions;

public class IdNotExistException extends RuntimeException {

    private String message;

    public IdNotExistException() {}

    public IdNotExistException(String message)
    {
        super(message);
        this.message = message;
    }
}

