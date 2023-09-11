package com.prato.onlinebooklibrary.exception;

public class EmptyResultException extends  RuntimeException{
    private static final String MESSAGE = "No ";
    public EmptyResultException(String object){
        super(MESSAGE+object+" found with the id");
    }
}
