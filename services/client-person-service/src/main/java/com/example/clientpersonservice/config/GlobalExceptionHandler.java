package com.example.clientpersonservice.config;

import com.example.clientpersonservice.common.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse<String>> handleValidationErrors(MethodArgumentNotValidException ex) {

        String[] errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .toArray(String[]::new);

        DefaultResponse<String> response = DefaultResponse.<String>builder()
                .success(false)
                .message("Error de validación")
                .status(HttpStatus.BAD_REQUEST.value())
                .code("VALIDATION_ERROR")
                .errors(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}