package com.spring.relaciones_1.exception;

import com.spring.relaciones_1.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                e.getMessage(),
                "error generico"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApplicantNotFound.class)
    public ResponseEntity<ErrorResponse> handleException(ApplicantNotFound e) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                e.getMessage(),
                "Applicant/s not found"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ResumeNotFound.class)
    public ResponseEntity<ErrorResponse> handleException(ResumeNotFound e) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                e.getMessage(),
                "Resume not found"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
