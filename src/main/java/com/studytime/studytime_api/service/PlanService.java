package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.PlanRequestDTO;
import com.studytime.studytime_api.dto.response.PlanResponseDTO;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDTO;
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

    public PlanResponseDTO create(Long teacherId, PlanRequestDTO planRequestDTO) {
        Teacher teacher = teacherService.getTeacherById(teacherId);

        if (planRepository.existsByTeacherAndTitle(teacher, planRequestDTO.getTitle())) {
            throw new PlanTitleAlreadyExistsException("Plan title already exists: " + planRequestDTO.getTitle());
        }

        Plan plan = modelMapper.map(planRequestDTO, Plan.class);
        plan.setTeacher(teacher);

        Plan savedPlan = planRepository.save(plan);

        PlanResponseDTO responseDTO = modelMapper.map(savedPlan, PlanResponseDTO.class);

        TeacherSummaryResponseDTO teacherSummary = modelMapper.map(savedPlan.getTeacher(), TeacherSummaryResponseDTO.class);
        responseDTO.setTeacherSummaryResponseDTO(teacherSummary);

        return responseDTO;
    }

    public List<PlanResponseDTO> findAllPlansByTeacherId(Long teacherId) {
        List<Plan> allPlansByTeacherId = planRepository.findAllByTeacherId(teacherId);
        return allPlansByTeacherId.stream()
                .map(plan -> modelMapper.map(plan, PlanResponseDTO.class))
                .toList();
    }
}

