package com.bosonit.formacion.backendfrontend.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends RuntimeException{
    ErrorResponse error;

    public UnprocessableEntityException(String message){
        super(message);
        this.error= new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), message);
    }
}
