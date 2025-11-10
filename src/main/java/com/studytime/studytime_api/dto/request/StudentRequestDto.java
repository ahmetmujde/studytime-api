package com.studytime.studytime_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentRequestDto {
    @NotBlank(message = "First name cannot be blank!")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank!")
    private String lastName;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Email must be valid!")
    private String email;

    @NotBlank(message = "Phone number cannot be blank!")
    private String phoneNumber;
}
