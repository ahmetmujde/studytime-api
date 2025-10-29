package com.studytime.studytime_api.controller;

import com.studytime.studytime_api.dto.request.TeacherSummaryRequestDTO;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDTO;
import com.studytime.studytime_api.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherSummaryResponseDTO> createTeacher(@Valid @RequestBody TeacherSummaryRequestDTO teacherSummaryRequestDTO) {
        TeacherSummaryResponseDTO savedTeacherSummaryResponseDTO = teacherService.saveTeacher(teacherSummaryRequestDTO);
        return ResponseEntity.ok(savedTeacherSummaryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TeacherSummaryResponseDTO>> findAllTeacher() {
        List<TeacherSummaryResponseDTO> teachersDTO = teacherService.findAllTeachers();
        if (teachersDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teachersDTO);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherSummaryResponseDTO> findAllTeacher(@PathVariable Long teacherId) {
        TeacherSummaryResponseDTO teacherDTO = teacherService.findTeacherByID(teacherId);
        if (Objects.isNull(teacherDTO)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teacherDTO);
    }

}
