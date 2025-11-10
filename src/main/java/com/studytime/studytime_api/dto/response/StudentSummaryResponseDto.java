package com.studytime.studytime_api.dto.response;

import lombok.Data;

@Data
public class StudentSummaryResponseDto {
    private String id;
    private String firstName;
    private String lastname;
    private String email;
    private String phoneNumber;
}
