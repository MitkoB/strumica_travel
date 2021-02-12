package com.webproject.strumica_travel.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class TouristAttractionNotFoundException extends RuntimeException {
    public TouristAttractionNotFoundException(Long id) {
        super(String.format("Tourist Attraction with this id: %d not found",id));
    }
}
