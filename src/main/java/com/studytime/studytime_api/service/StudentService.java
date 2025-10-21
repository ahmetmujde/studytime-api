package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.StudentRequestDTO;
import com.studytime.studytime_api.dto.response.StudentSummaryResponseDTO;
import com.studytime.studytime_api.entity.Student;
import com.studytime.studytime_api.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherService teacherService;
    private final ModelMapper modelMapper;


    public StudentService(StudentRepository studentRepository, TeacherService teacherService, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.teacherService = teacherService;
        this.modelMapper = modelMapper;
    }

    public StudentSummaryResponseDTO create(StudentRequestDTO studentRequestDTO) {
        teacherService.isTeacherExistByEmail(studentRequestDTO.getEmail());

        Student mapStudent = modelMapper.map(studentRequestDTO, Student.class);
        Student savedStudent = studentRepository.save(mapStudent);

        return modelMapper.map(savedStudent, StudentSummaryResponseDTO.class);
    }
}
