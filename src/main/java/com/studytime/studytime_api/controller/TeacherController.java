package com.studytime.studytime_api.controller;

import com.studytime.studytime_api.dto.request.TeacherSummaryRequestDto;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDto;
import com.studytime.studytime_api.service.TeacherService;
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
import java.util.Objects;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherSummaryResponseDto> createTeacher(
            @Valid @RequestBody TeacherSummaryRequestDto teacherSummaryRequestDTO) {

        TeacherSummaryResponseDto createdTeacher = teacherService.createTeacher(teacherSummaryRequestDTO);
        return ResponseEntity.ok(createdTeacher);
    }

    @GetMapping
    public ResponseEntity<List<TeacherSummaryResponseDto>> getAllTeachers() {
        List<TeacherSummaryResponseDto> foundTeachers = teacherService.getAllTeachers();

        if (foundTeachers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(foundTeachers);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherSummaryResponseDto> getTeacherById(@PathVariable Long teacherId) {
        TeacherSummaryResponseDto teacher = teacherService.getTeacherDtoById(teacherId);

        if (Objects.isNull(teacher)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherSummaryResponseDto> updateTeacher(
            @PathVariable Long teacherId,
            @Valid @RequestBody TeacherSummaryRequestDto teacherSummaryRequestDTO) {

        TeacherSummaryResponseDto updatedTeacher = teacherService.updateTeacher(teacherId, teacherSummaryRequestDTO);

        return ResponseEntity.ok(updatedTeacher);
    }

}
