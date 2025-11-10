package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.StudentRequestDto;
import com.studytime.studytime_api.dto.response.StudentSummaryResponseDto;
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

    public StudentService(
            StudentRepository studentRepository,
            UserValidationService userValidationService,
            ModelMapper modelMapper) {

        this.studentRepository = studentRepository;
        this.userValidationService = userValidationService;
        this.modelMapper = modelMapper;
    }

    public StudentSummaryResponseDto create(StudentRequestDto studentRequestDTO) {
        userValidationService.checkEmailNotUsed(studentRequestDTO.getEmail());
        userValidationService.checkPhoneNotUsed(studentRequestDTO.getPhoneNumber());

        Student mapStudent = modelMapper.map(studentRequestDTO, Student.class);
        Student savedStudent = studentRepository.save(mapStudent);

        return modelMapper.map(savedStudent, StudentSummaryResponseDto.class);
    }

    public List<StudentSummaryResponseDto> findAllStudents() {
        List<Student> allStudents = studentRepository.findAll();

        return  allStudents.stream()
                .map(student -> modelMapper.map(student, StudentSummaryResponseDto.class))
                .toList();
    }

    public StudentSummaryResponseDto getStudentSummaryResponseDtoByStudentId(Long studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if (studentOpt.isEmpty())
            throw new StudentNotFoundException(studentId);

        return modelMapper.map(studentOpt.get(), StudentSummaryResponseDto.class);
    }

    public StudentSummaryResponseDto updateStudent(Long id, StudentRequestDto dto) {

        Student student = getStudentById(id);

        if (!userValidationService.isSameEmail(student.getEmail(), dto.getEmail())) {
            userValidationService.checkEmailNotUsed(dto.getEmail());
        }

        if (!userValidationService.isSamePhoneNumber(student.getPhoneNumber(), dto.getPhoneNumber())) {
            userValidationService.checkPhoneNotUsed(dto.getPhoneNumber());
        }

        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());

        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentSummaryResponseDto.class);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

}
