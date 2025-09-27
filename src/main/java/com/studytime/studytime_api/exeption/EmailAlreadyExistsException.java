package com.studytime.studytime_api.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistsException  extends BaseBusinessException{
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email, HttpStatus.CONFLICT);
    }
}
