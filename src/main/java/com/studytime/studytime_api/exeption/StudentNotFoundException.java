package com.studytime.studytime_api.exeption;


public class StudentNotFoundException extends BaseBusinessException {
    public StudentNotFoundException(Long studentId) {
        super("Student not found with id: " + studentId);
    }
}
