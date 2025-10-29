package com.studytime.studytime_api.controller;

import com.studytime.studytime_api.dto.request.StudentRequestDTO;
import com.studytime.studytime_api.dto.response.StudentSummaryResponseDTO;
import com.studytime.studytime_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<StudentSummaryResponseDTO>> findAllStudent() {
        List<StudentSummaryResponseDTO> students = studentService.findAllStudents();

        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentSummaryResponseDTO> findStudentByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentByStudentId(studentId));
    }
}
