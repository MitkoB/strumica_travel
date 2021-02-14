package com.webproject.strumica_travel.model.exception;

public class RouteAlreadyExistsInFavoriteCart extends RuntimeException{
    public RouteAlreadyExistsInFavoriteCart() {
        super("This item already exists in favorite cart");
    }
}
