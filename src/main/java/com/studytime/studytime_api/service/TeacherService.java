package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.TeacherSummaryRequestDto;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDto;
import com.studytime.studytime_api.entity.Teacher;
import com.studytime.studytime_api.exeption.TeacherNotFoundException;
import com.studytime.studytime_api.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;
    private final UserValidationService userValidationService;

    public TeacherService( ModelMapper modelMapper,
                           TeacherRepository teacherRepository,
                           UserValidationService userValidationService) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
        this.userValidationService = userValidationService;
    }

    public TeacherSummaryResponseDto createTeacher(TeacherSummaryRequestDto teacherSummaryRequestDTO) {
        userValidationService.checkEmailNotUsed(teacherSummaryRequestDTO.getEmail());
        userValidationService.checkPhoneNotUsed(teacherSummaryRequestDTO.getPhoneNumber());

        Teacher teacher = modelMapper.map(teacherSummaryRequestDTO,Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return modelMapper.map(savedTeacher, TeacherSummaryResponseDto.class);
    }

    public List<TeacherSummaryResponseDto> getAllTeachers() {
        List<Teacher> allTeachers = teacherRepository.findAll();

        return allTeachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherSummaryResponseDto.class))
                .toList();
    }

    public TeacherSummaryResponseDto updateTeacher(Long id, TeacherSummaryRequestDto dto) {
        Teacher teacher = getTeacherById(id);

        if (!userValidationService.isSameEmail(teacher.getEmail(), dto.getEmail())) {
            userValidationService.checkEmailNotUsed(dto.getEmail());
        }

        if (!userValidationService.isSamePhoneNumber(teacher.getPhoneNumber(), dto.getPhoneNumber())) {
            userValidationService.checkPhoneNotUsed(dto.getPhoneNumber());
        }

        teacher.setEmail(dto.getEmail());
        teacher.setPhoneNumber(dto.getPhoneNumber());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return modelMapper.map(updatedTeacher, TeacherSummaryResponseDto.class);
    }

    public TeacherSummaryResponseDto getTeacherDtoById(Long teacherID) {
        Teacher teacher = getTeacherById(teacherID);
        return modelMapper.map(teacher, TeacherSummaryResponseDto.class);
    }

    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));
    }

}
