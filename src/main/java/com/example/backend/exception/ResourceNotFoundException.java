package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
