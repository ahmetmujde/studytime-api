package com.studytime.studytime_api.controller;


import com.studytime.studytime_api.dto.request.PlanRequestDTO;
import com.studytime.studytime_api.dto.response.PlanResponseDTO;
import com.studytime.studytime_api.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanResponseDTO> create(@RequestParam Long teacherId, @Valid @RequestBody PlanRequestDTO planRequestDTO){
        PlanResponseDTO createdPlan = planService.create(teacherId,planRequestDTO);
        return ResponseEntity.ok(createdPlan);
    }
}
