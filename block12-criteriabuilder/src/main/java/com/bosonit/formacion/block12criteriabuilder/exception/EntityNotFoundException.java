package com.bosonit.formacion.block12criteriabuilder.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException{
    ErrorResponse error;
    public EntityNotFoundException(String message){
        super(message);
        this.error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), message);
    }


}
