package com.studytime.studytime_api.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PhoneNumberAlreadyExistsException extends BaseBusinessException{
    public PhoneNumberAlreadyExistsException(String phoneNumber) {
        super("Phone number already exist: "+ phoneNumber);
    }
}
