package com.webproject.strumica_travel.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class InvalidTouristAttractionException extends RuntimeException{
    public InvalidTouristAttractionException() {
        super(String.format("Invalid Tourist Attraction Exception"));
    }
}
