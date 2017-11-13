package com.tbsoares.campaign.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseWrapper {
    private String message;
    private HttpStatus status;

    private ResponseWrapper(HttpStatus httpStatus) {
        this.status = httpStatus;
    }

    public static ResponseWrapper status(HttpStatus httpStatus) {
        return new ResponseWrapper(httpStatus);
    }

    public ResponseWrapper message(String message) {
        this.message = message;
        return this;
    }
}
