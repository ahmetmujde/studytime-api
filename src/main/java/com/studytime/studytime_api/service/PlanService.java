package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.PlanRequestDto;
import com.studytime.studytime_api.dto.response.PlanResponseDto;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDto;
import com.studytime.studytime_api.entity.Plan;
import com.studytime.studytime_api.entity.Teacher;
import com.studytime.studytime_api.exeption.PlanTitleAlreadyExistsException;
import com.studytime.studytime_api.repository.PlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    public PlanService(PlanRepository planRepository,
                       TeacherService teacherService,
                       ModelMapper modelMapper) {
        this.planRepository = planRepository;
        this.teacherService = teacherService;
        this.modelMapper = modelMapper;
    }

    public PlanResponseDto create(Long teacherId, PlanRequestDto planRequestDTO) {
        Teacher teacher = teacherService.getTeacherById(teacherId);

        if (planRepository.existsByTeacherAndTitle(teacher, planRequestDTO.getTitle())) {
            throw new PlanTitleAlreadyExistsException("Plan title already exists: " + planRequestDTO.getTitle());
        }

        Plan plan = modelMapper.map(planRequestDTO, Plan.class);
        plan.setTeacher(teacher);

        Plan savedPlan = planRepository.save(plan);

        PlanResponseDto responseDTO = modelMapper.map(savedPlan, PlanResponseDto.class);

        TeacherSummaryResponseDto teacherSummary = modelMapper.map(savedPlan.getTeacher(), TeacherSummaryResponseDto.class);
        responseDTO.setTeacherSummaryResponseDTO(teacherSummary);

        return responseDTO;
    }

    public List<PlanResponseDto> findAllPlansByTeacherId(Long teacherId) {
        List<Plan> allPlansByTeacherId = planRepository.findAllByTeacherId(teacherId);
        return allPlansByTeacherId.stream()
                .map(plan -> modelMapper.map(plan, PlanResponseDto.class))
                .toList();
    }
}

