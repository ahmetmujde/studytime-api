package com.studytime.studytime_api.dto.response;

import lombok.Data;

@Data
public class TeacherSummaryResponseDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
