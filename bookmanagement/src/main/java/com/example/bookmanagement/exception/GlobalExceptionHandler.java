package com.example.bookmanagement.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateKeyException(DuplicateKeyException ex) {
        Map<String, String> error = new HashMap<>();

        // Check which field caused the error
        if (ex.getMessage().contains("email")) {
            error.put("error", "Email already exists! Please use a different one.");
        } else if (ex.getMessage().contains("isbn")) {
            error.put("error", "Book ISBN already exists in the library.");
        } else {
            error.put("error", "Duplicate data entry detected.");
        }

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}