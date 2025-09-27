package com.studytime.studytime_api.exeption;

import org.springframework.http.HttpStatus;

public class BaseBusinessException extends RuntimeException {
    private final HttpStatus status;

    public BaseBusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
