package com.bosonit.formacion.block7crudvalidation.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

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
