package com.bosonit.formacion.block7crudvalidation.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    Date timeStamp;
    int httpCode;
    String message;

    public ErrorResponse(int httpCode, String message){
        this.timeStamp = new Date();
        this.httpCode = httpCode;
        this.message = message;
    }

}
