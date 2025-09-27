package com.studytime.studytime_api.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlanTitleAlreadyExistsException extends  BaseBusinessException{

    public PlanTitleAlreadyExistsException(String message, HttpStatus status) {
        super(message, status);
    }
}