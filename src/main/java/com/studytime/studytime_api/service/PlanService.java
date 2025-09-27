package com.studytime.studytime_api.service;

import com.studytime.studytime_api.dto.request.PlanRequestDTO;
import com.studytime.studytime_api.dto.response.PlanResponseDTO;
import com.studytime.studytime_api.dto.response.TeacherSummaryResponseDTO;
import com.studytime.studytime_api.entity.Plan;
import com.studytime.studytime_api.entity.Teacher;
import com.studytime.studytime_api.exeption.PlanTitleAlreadyExistsException;
import com.studytime.studytime_api.exeption.TeacherNotFoundException;
import com.studytime.studytime_api.repository.PlanRepository;
import com.studytime.studytime_api.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final TeacherRepository teacherRepository;


    public PlanService(PlanRepository planRepository, TeacherRepository teacherRepository) {
        this.planRepository = planRepository;
        this.teacherRepository = teacherRepository;
    }

    public PlanResponseDTO create(Long teacherId, PlanRequestDTO planRequestDTO) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher is Not found!", HttpStatus.NOT_FOUND));

        boolean exists = planRepository.existsByTeacherAndTitle(teacher, planRequestDTO.getTitle());
        if (exists) {
            throw new PlanTitleAlreadyExistsException("Same plan title not be created! " +  planRequestDTO.getTitle(), HttpStatus.CONFLICT);
        }

        Plan plan = new Plan();
        plan.setTitle(planRequestDTO.getTitle());
        plan.setDescription(planRequestDTO.getDescription());
        plan.setPrice(planRequestDTO.getPrice());
        plan.setPlanType(planRequestDTO.getPlanType());
        plan.setTeacher(teacher);
        Plan savedPlan = planRepository.save(plan);

        PlanResponseDTO planResponseDTO = new PlanResponseDTO();

        planResponseDTO.setId(savedPlan.getId());
        planResponseDTO.setTitle(savedPlan.getTitle());
        planResponseDTO.setDescription(savedPlan.getDescription());
        planResponseDTO.setPrice(savedPlan.getPrice());
        planResponseDTO.setPlanType(savedPlan.getPlanType());

        TeacherSummaryResponseDTO teacherSummaryResponseDTO = new TeacherSummaryResponseDTO();


        teacherSummaryResponseDTO.setId(savedPlan.getTeacher().getId().toString());
        teacherSummaryResponseDTO.setFirstname(savedPlan.getTeacher().getFirstName());
        teacherSummaryResponseDTO.setLastname(savedPlan.getTeacher().getLastName());
        teacherSummaryResponseDTO.setEmail(savedPlan.getTeacher().getEmail());
        teacherSummaryResponseDTO.setPhoneNumber(savedPlan.getTeacher().getPhoneNumber());

        planResponseDTO.setTeacherSummaryResponseDTO(teacherSummaryResponseDTO);

        return planResponseDTO;
    }
}
