package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.FavoriteCart;
import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;

import java.util.List;

public interface FavoriteCartService {
    List<TouristAttraction> listAllTouristAttractionsInFavoriteCart(Long cartId);
    FavoriteCart addTouristAttractionsToFavoriteCart(String username, Long attractionId);
    FavoriteCart getActiveFavoriteCart(String username);
    List<Route> listAllRoutesInFavoriteCart(Long cartId);
    FavoriteCart addRouteToFavoriteCart(String username, Long routeId);
}
