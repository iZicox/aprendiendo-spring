package com.springboot.practica_2.exception;

import com.springboot.practica_2.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNoEncontrado.class)
    public ResponseEntity<ErrorResponseDTO> handleException(ProductoNoEncontrado ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO("No encontrado", ex.getMessage()));
    }
}
