package com.webproject.strumica_travel.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(Long id) {
        super(String.format("Route with this id: %d not found",id));
    }
}
