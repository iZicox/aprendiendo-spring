package com.springboot.practica4.exception;

import com.springboot.practica4.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Product Not Found",
                e.getMessage()
        );
        return new  ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException e){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Product Not Found",
                e.getMessage()
        );
        return new  ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductListException.class)
    public ResponseEntity<?> handleProductListException(ProductListException e){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "List Not Found",
                e.getMessage()
        );
        return new  ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
