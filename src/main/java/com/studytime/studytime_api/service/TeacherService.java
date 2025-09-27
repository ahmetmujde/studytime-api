package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.TeacherSummaryRequestDTO;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDTO;
import com.studytime.studytime_api.entity.Teacher;
import com.studytime.studytime_api.exeption.EmailAlreadyExistsException;
import com.studytime.studytime_api.exeption.PhoneNumberAlreadyExistsException;
import com.studytime.studytime_api.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    public TeacherSummaryResponseDTO saveTeacher(TeacherSummaryRequestDTO teacherSummaryRequestDTO) {
        if (teacherRepository.existsByEmail(teacherSummaryRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(teacherSummaryRequestDTO.getEmail());
        }

        if (teacherRepository.existsByPhoneNumber(teacherSummaryRequestDTO.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(teacherSummaryRequestDTO.getPhoneNumber());
        }

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherSummaryRequestDTO.getFirstName());
        teacher.setLastName(teacherSummaryRequestDTO.getLastName());
        teacher.setPhoneNumber(teacherSummaryRequestDTO.getPhoneNumber());
        teacher.setEmail(teacherSummaryRequestDTO.getEmail());

        Teacher savedTeacher = teacherRepository.save(teacher);

        TeacherSummaryResponseDTO teacherSummaryResponseDTO = new TeacherSummaryResponseDTO();

        teacherSummaryResponseDTO.setId(savedTeacher.getId().toString());
        teacherSummaryResponseDTO.setFirstname(savedTeacher.getFirstName());
        teacherSummaryResponseDTO.setLastname(savedTeacher.getLastName());
        teacherSummaryResponseDTO.setPhoneNumber(savedTeacher.getPhoneNumber());
        teacherSummaryResponseDTO.setEmail(savedTeacher.getEmail());

        return teacherSummaryResponseDTO;
    }
}
