package com.studytime.studytime_api.dto.response;

import lombok.Data;

@Data
public class TeacherSummaryResponseDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
}
