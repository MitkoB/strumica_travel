package com.webproject.strumica_travel.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class FavoriteCartNotFoundException extends RuntimeException {
    public FavoriteCartNotFoundException(Long cartId) {
        super(String.format("Favorite cart with this id: %d not found",cartId));
    }
}
