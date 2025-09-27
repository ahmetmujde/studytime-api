package com.studytime.studytime_api.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends  BaseBusinessException {
    public TeacherNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}

