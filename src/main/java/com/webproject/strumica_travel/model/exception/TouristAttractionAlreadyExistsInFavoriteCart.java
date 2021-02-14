package com.webproject.strumica_travel.model.exception;

public class TouristAttractionAlreadyExistsInFavoriteCart extends RuntimeException{
    public TouristAttractionAlreadyExistsInFavoriteCart() {
        super("This item already exists in favorite cart");
    }
}
