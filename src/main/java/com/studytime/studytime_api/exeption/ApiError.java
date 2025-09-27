package com.studytime.studytime_api.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private int status;       // HTTP status code
    private String error;     // Error title (e.g., "Validation Failed")
    private String message;   // Detailed message
    private String path;      // Request path (/api/teachers)
    private LocalDateTime timestamp; // When error occurred
    private Map<String, String> fieldErrors; // For validation errors (optional)
}
