package com.studytime.studytime_api.dto.request;

import lombok.Data;

@Data
public class TeacherSummaryRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
