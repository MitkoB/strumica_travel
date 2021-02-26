package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    List<Route> findAll();
    Optional<Route> findById(Long id);
    Optional<Route> findByName(String name);
    Optional<Route> save(String name, String description,String duration,String image,List<Long> touristAttractionsIds);
    Optional<Route> edit(Long id, String name,String description,String duration,String image,List<Long> touristAttractionsIds);
    Optional<Route> setLikes(Long id);
    List<Route> searchRoute(String name);
    void deleteById(Long id);
}
