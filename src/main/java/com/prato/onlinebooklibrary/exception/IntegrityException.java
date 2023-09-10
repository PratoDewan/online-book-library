package com.prato.onlinebooklibrary.exception;

public class IntegrityException extends Exception{
    private static final String MESSAGE = "Error! Duplicate Entry detected!";

    public IntegrityException() {
        super(MESSAGE);
    }
}
