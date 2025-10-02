package com.studytime.studytime_api.controller;

import com.studytime.studytime_api.dto.request.StudentRequestDTO;
import com.studytime.studytime_api.dto.response.StudentSummaryResponseDTO;
import com.studytime.studytime_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentSummaryResponseDTO> create(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.ok(studentService.create(studentRequestDTO));
    }
}
