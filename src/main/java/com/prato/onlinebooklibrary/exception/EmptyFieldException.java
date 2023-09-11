package com.prato.onlinebooklibrary.exception;

public class EmptyFieldException extends RuntimeException{
    private static final String MESSAGE = "Error! ";

    public EmptyFieldException(String fieldName) {
        super(MESSAGE + fieldName + " must be included and can not be empty!");
    }
}
