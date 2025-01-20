package com.aruizmonta.forohubchallenge.infrastructure.utils.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
