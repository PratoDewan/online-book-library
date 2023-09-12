package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.exception.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleMissingException(Exception ex){
        return new ResponseEntity<>("Error! Email already exists!", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlingEnumExceptions(){
        return new ResponseEntity<>(new InvalidRoleException().getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<?> handleIntegrityException(SQLIntegrityConstraintViolationException ex) {
        Pattern pattern = Pattern.compile("'(\\S+@\\S+)'");
        Matcher matcher = pattern.matcher(ex.getMessage());
        StringBuilder email=new StringBuilder();
        // Find the first matching group
        if (matcher.find()) {
            email.append(matcher.group(1));
        }
        return new ResponseEntity<>(new IntegrityException(email.toString()).getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintException(ConstraintViolationException ex) {
        StringBuilder emptyFields = new StringBuilder();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            emptyFields.append(propertyPath).append(", ");
        }
        emptyFields.delete(emptyFields.length()-2,emptyFields.length());
        return new ResponseEntity<>(new EmptyFieldException(emptyFields.toString()).getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<?> handleEmptyDataException(EmptyResultDataAccessException ex){
        return new ResponseEntity<>(new EmptyResultException(ex.getMessage()).getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<>(new InvalidQueryException(ex.getMessage()).getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({IllegalOperationException.class})
    public ResponseEntity<?> handleIllegalOperationException(IllegalOperationException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
