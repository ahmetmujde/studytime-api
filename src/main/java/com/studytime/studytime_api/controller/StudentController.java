package com.studytime.studytime_api.controller;

import com.studytime.studytime_api.dto.request.StudentRequestDto;
import com.studytime.studytime_api.dto.response.StudentSummaryResponseDto;
import com.studytime.studytime_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<StudentSummaryResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto studentRequestDTO) {

        StudentSummaryResponseDto savedStudent = studentService.create(studentRequestDTO);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentSummaryResponseDto>> getAllStudents() {
        List<StudentSummaryResponseDto> students = studentService.findAllStudents();

        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentSummaryResponseDto> getStudentById
            (@PathVariable Long studentId) {

        StudentSummaryResponseDto student = studentService.getStudentSummaryResponseDtoByStudentId(studentId);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentSummaryResponseDto> updateStudent(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentRequestDto studentRequestDTO) {

        StudentSummaryResponseDto updatedStudent =
                studentService.updateStudent(studentId, studentRequestDTO);

        return ResponseEntity.ok(updatedStudent);
    }
}
