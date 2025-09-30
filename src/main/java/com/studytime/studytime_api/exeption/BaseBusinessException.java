package com.studytime.studytime_api.exeption;

public class BaseBusinessException extends RuntimeException {

    public BaseBusinessException(String message) {
        super(message);
    }
}
