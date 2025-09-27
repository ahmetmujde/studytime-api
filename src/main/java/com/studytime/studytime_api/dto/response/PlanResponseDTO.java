package com.studytime.studytime_api.dto.response;

import com.studytime.studytime_api.enums.PlanType;
import lombok.Data;

@Data
public class PlanResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private PlanType planType;
    private TeacherSummaryResponseDTO teacherSummaryResponseDTO;
}
