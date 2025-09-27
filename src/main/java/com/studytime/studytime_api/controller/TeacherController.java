package com.studytime.studytime_api.controller;

import com.studytime.studytime_api.dto.request.TeacherSummaryRequestDTO;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDTO;
import com.studytime.studytime_api.entity.Teacher;
import com.studytime.studytime_api.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
}
