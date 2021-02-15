package com.webproject.strumica_travel.model.exception;

public class PermisionDeniedException extends RuntimeException{
    public PermisionDeniedException() {
        super("You don't have access");
    }
}
