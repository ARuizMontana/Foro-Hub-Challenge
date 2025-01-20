package com.aruizmonta.forohubchallenge.infrastructure.utils.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
