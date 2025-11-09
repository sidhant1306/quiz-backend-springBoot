package org.example.springboot_project_first.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// A standardized error printing class :
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;     // 404, 400, 500...
    private String error;   // NOT_FOUND, BAD_REQUEST...
    private String message; // Human-readable detail
}
