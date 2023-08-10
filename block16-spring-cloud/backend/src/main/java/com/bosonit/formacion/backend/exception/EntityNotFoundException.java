package com.bosonit.formacion.backend.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {

    ErrorResponse error;

    public EntityNotFoundException(String message){
        super(message);
        this.error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), message);
    }
}
