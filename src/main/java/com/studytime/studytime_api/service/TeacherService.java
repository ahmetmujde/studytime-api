package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.TeacherSummaryRequestDTO;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDTO;
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

    public TeacherSummaryResponseDTO saveTeacher(TeacherSummaryRequestDTO teacherSummaryRequestDTO) {
        userValidationService.checkEmailNotUsed(teacherSummaryRequestDTO.getEmail());
        userValidationService.checkPhoneNotUsed(teacherSummaryRequestDTO.getPhoneNumber());

        Teacher teacher = modelMapper.map(teacherSummaryRequestDTO,Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return modelMapper.map(savedTeacher,TeacherSummaryResponseDTO.class);
    }

    public List<TeacherSummaryResponseDTO> findAllTeachers() {
        List<Teacher> allTeachers = teacherRepository.findAll();
        return allTeachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherSummaryResponseDTO.class))
                .toList();
    }

    public TeacherSummaryResponseDTO findTeacherByID(Long teacherID) {
        Teacher teacher = getTeacherById(teacherID);
        return modelMapper.map(teacher, TeacherSummaryResponseDTO.class);
    }

    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));
    }

}
