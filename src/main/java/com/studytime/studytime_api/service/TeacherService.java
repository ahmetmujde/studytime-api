package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.TeacherSummaryRequestDTO;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDTO;
import com.studytime.studytime_api.entity.Teacher;
import com.studytime.studytime_api.exeption.EmailAlreadyExistsException;
import com.studytime.studytime_api.exeption.PhoneNumberAlreadyExistsException;
import com.studytime.studytime_api.exeption.TeacherNotFoundException;
import com.studytime.studytime_api.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public TeacherSummaryResponseDTO saveTeacher(TeacherSummaryRequestDTO teacherSummaryRequestDTO) {
        if (teacherRepository.existsByEmail(teacherSummaryRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(teacherSummaryRequestDTO.getEmail());
        }

        if (teacherRepository.existsByPhoneNumber(teacherSummaryRequestDTO.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(teacherSummaryRequestDTO.getPhoneNumber());
        }

        Teacher teacher = modelMapper.map(teacherSummaryRequestDTO,Teacher.class);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return modelMapper.map(savedTeacher,TeacherSummaryResponseDTO.class);
    }

    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + teacherId));
    }
}
