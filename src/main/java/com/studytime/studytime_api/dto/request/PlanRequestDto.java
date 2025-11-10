package com.studytime.studytime_api.dto.request;

import com.studytime.studytime_api.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PlanRequestDto {

    @NotBlank(message = "Title cannot be blank!")
    private String title;

    private String description;

    @NotNull(message = "Price cannot be null!")
    @PositiveOrZero(message = "Price must be positive or zero!")
    private Double price;

    @NotNull(message = "Plan type cannot be null!")
    private PlanType planType;
}
