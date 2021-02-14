package com.webproject.strumica_travel.service.impl;

import com.webproject.strumica_travel.model.FavoriteCart;
import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.model.enumeration.FavoriteCartStatus;
import com.webproject.strumica_travel.model.exception.*;
import com.webproject.strumica_travel.repository.FavoriteCartRepository;
import com.webproject.strumica_travel.repository.RouteRepository;
import com.webproject.strumica_travel.repository.TouristAttractionRepository;
import com.webproject.strumica_travel.repository.UserRepository;
import com.webproject.strumica_travel.service.FavoriteCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteCartServiceImpl implements FavoriteCartService {
    private final FavoriteCartRepository favoriteCartRepository;
    private final UserRepository userRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    private final RouteRepository routeRepository;
    public FavoriteCartServiceImpl(FavoriteCartRepository favoriteCartRepository, UserRepository userRepository, TouristAttractionRepository touristAttractionRepository, RouteRepository routeRepository) {
        this.favoriteCartRepository = favoriteCartRepository;
        this.userRepository = userRepository;
        this.touristAttractionRepository = touristAttractionRepository;
        this.routeRepository = routeRepository;
    }
    @Override
    public List<TouristAttraction> listAllTouristAttractionsInFavoriteCart(Long cartId) {
        FavoriteCart favoriteCart=favoriteCartRepository.findById(cartId).orElseThrow(()->new FavoriteCartNotFoundException(cartId));
        return favoriteCart.getTouristAttractionList();
    }

    @Override
    public FavoriteCart addTouristAttractionsToFavoriteCart(String username, Long attractionId) {
        TouristAttraction touristAttraction=touristAttractionRepository.findById(attractionId).orElseThrow(()->new TouristAttractionNotFoundException(attractionId));
        FavoriteCart favoriteCart=this.getActiveFavoriteCart(username);
        if(favoriteCart.getTouristAttractionList().stream().filter(i->i.getId().equals(attractionId)).collect(Collectors.toList()).size()>0)
        {
            throw new TouristAttractionAlreadyExistsInFavoriteCart();
        }
        favoriteCart.getTouristAttractionList().add(touristAttraction);
        return favoriteCartRepository.save(favoriteCart);
    }

    @Override
    public FavoriteCart getActiveFavoriteCart(String username) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException());
        if(!favoriteCartRepository.findByUserAndFavoriteCartStatus(user, FavoriteCartStatus.CREATED).isPresent())
        {
            FavoriteCart favoriteCart=new FavoriteCart(user);
            return favoriteCartRepository.save(favoriteCart);
        }
        else{
            return favoriteCartRepository.findByUserAndFavoriteCartStatus(user,FavoriteCartStatus.CREATED).get();
        }

    }

    @Override
    public List<Route> listAllRoutesInFavoriteCart(Long cartId) {
        FavoriteCart favoriteCart=favoriteCartRepository.findById(cartId).orElseThrow(()->new FavoriteCartNotFoundException(cartId));
        return favoriteCart.getRouteList();
    }

    @Override
    public FavoriteCart addRouteToFavoriteCart(String username, Long routeId) {
        Route route=routeRepository.findById(routeId).orElseThrow(()->new RouteNotFoundException(routeId));
        FavoriteCart favoriteCart=this.getActiveFavoriteCart(username);
        if(favoriteCart.getRouteList().stream().filter(i->i.getId().equals(routeId)).collect(Collectors.toList()).size()>0)
        {
            throw new RouteAlreadyExistsInFavoriteCart();
        }
        favoriteCart.getRouteList().add(route);
        return favoriteCartRepository.save(favoriteCart);
    }
}
