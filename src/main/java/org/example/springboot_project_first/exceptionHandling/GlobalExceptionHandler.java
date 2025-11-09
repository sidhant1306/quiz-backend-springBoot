package org.example.springboot_project_first.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.example.springboot_project_first.exceptionHandling.ErrorResponse;
public class GlobalExceptionHandler {
    /**
     * This method handles ONLY ResourceNotFoundException.
     *
     * @ExceptionHandler tells Spring: "If a ResourceNotFoundException bubbles up,
     * call THIS method to convert it into a proper HTTP response."
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException rx) {
        ErrorResponse body = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                rx.getMessage()
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        // In real apps, you'd also log the exception here with a logger.
        ErrorResponse body = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),  // 500
                "INTERNAL_ERROR",
                "Something went wrong. Please try again later."
        );

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
