package com.bosonit.formacion.block7crudvalidation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    ErrorResponse error;
    public EntityNotFoundException(String message){
        super(message);
        this.error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), message);
    }


}
