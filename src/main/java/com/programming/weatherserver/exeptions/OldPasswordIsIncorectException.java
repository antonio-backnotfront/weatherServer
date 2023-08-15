package com.programming.weatherserver.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OldPasswordIsIncorectException extends RuntimeException {
    public OldPasswordIsIncorectException(String message) {
        super(message);
    }
}
