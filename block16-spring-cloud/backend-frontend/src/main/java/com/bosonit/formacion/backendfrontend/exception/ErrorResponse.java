package com.bosonit.formacion.backendfrontend.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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
