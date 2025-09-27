package com.studytime.studytime_api.dto.request;

import com.studytime.studytime_api.enums.PlanType;
import lombok.Data;

@Data
public class PlanRequestDTO {
    private String title;
    private String description;
    private Double price;
    private PlanType planType;
}
