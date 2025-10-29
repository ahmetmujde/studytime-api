package com.studytime.studytime_api.exeption;


public class TeacherNotFoundException extends BaseBusinessException {
    public TeacherNotFoundException(Long message) {
        super("Teacher not found with id: "+ message);
    }
}

