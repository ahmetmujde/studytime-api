package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.StudentRequestDTO;
import com.studytime.studytime_api.dto.response.StudentSummaryResponseDTO;
import com.studytime.studytime_api.entity.Student;
import com.studytime.studytime_api.exeption.StudentNotFoundException;
import com.studytime.studytime_api.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final UserValidationService userValidationService;

    public StudentService(StudentRepository studentRepository, UserValidationService userValidationService, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.userValidationService = userValidationService;
        this.modelMapper = modelMapper;
    }

    public StudentSummaryResponseDTO create(StudentRequestDTO studentRequestDTO) {
        userValidationService.checkEmailNotUsed(studentRequestDTO.getEmail());
        userValidationService.checkPhoneNotUsed(studentRequestDTO.getPhoneNumber());

        Student mapStudent = modelMapper.map(studentRequestDTO, Student.class);
        Student savedStudent = studentRepository.save(mapStudent);

        return modelMapper.map(savedStudent, StudentSummaryResponseDTO.class);
    }

    public List<StudentSummaryResponseDTO> findAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return  allStudents.stream()
                .map(student -> modelMapper.map(student, StudentSummaryResponseDTO.class))
                .toList();
    }

    public StudentSummaryResponseDTO getStudentByStudentId(Long studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if (studentOpt.isEmpty())
            throw new StudentNotFoundException(studentId);

        return modelMapper.map(studentOpt.get(), StudentSummaryResponseDTO.class);
    }
}
