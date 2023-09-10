package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.exception.EmptyFieldException;
import com.prato.onlinebooklibrary.exception.IntegrityException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalController {
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<?> handleIntegrityException() {
        return new ResponseEntity<>((new IntegrityException()).getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintException() {
        return new ResponseEntity<>((new EmptyFieldException("Field")).getMessage(), HttpStatus.CONFLICT);
    }
}
