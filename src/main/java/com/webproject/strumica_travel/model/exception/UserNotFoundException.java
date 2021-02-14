package com.webproject.strumica_travel.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found exception ");
    }
}
