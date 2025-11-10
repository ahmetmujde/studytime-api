package com.studytime.studytime_api.service;


import com.studytime.studytime_api.exeption.EmailAlreadyExistsException;
import com.studytime.studytime_api.exeption.PhoneNumberAlreadyExistsException;
import com.studytime.studytime_api.repository.StudentRepository;
import com.studytime.studytime_api.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserValidationService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public UserValidationService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public void checkEmailNotUsed(String email) {
        if (studentRepository.existsByEmail(email) || teacherRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    public void checkPhoneNotUsed(String phoneNumber) {
        if (studentRepository.existsByPhoneNumber(phoneNumber) || teacherRepository.existsByPhoneNumber(phoneNumber)) {
            throw new PhoneNumberAlreadyExistsException(phoneNumber);
        }
    }

    public boolean isSameEmail(String currentEmail, String newEmail) {
        return Objects.equals(currentEmail, newEmail);
    }

    public boolean isSamePhoneNumber(String currentPhoneNumber, String newPhoneNumber) {
        return Objects.equals(currentPhoneNumber, newPhoneNumber);
    }
}
