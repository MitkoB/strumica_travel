package com.webproject.strumica_travel.service.impl;

import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.exception.InvalidTouristAttractionException;
import com.webproject.strumica_travel.model.exception.RouteNotFoundException;
import com.webproject.strumica_travel.repository.RouteRepository;
import com.webproject.strumica_travel.repository.TouristAttractionRepository;
import com.webproject.strumica_travel.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    public RouteServiceImpl(RouteRepository routeRepository, TouristAttractionRepository touristAttractionRepository) {
        this.routeRepository = routeRepository;
        this.touristAttractionRepository = touristAttractionRepository;
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }

    @Override
    public Optional<Route> findByName(String name) {
        return routeRepository.findByName(name);
    }

    @Override
    public Optional<Route> save(String name,String description,String duration,String image,List<Long> touristAttractionsIds) {
        List<TouristAttraction> touristAttractions=touristAttractionRepository.findAllById(touristAttractionsIds);
        if(touristAttractions==null || touristAttractions.isEmpty())
        {
            throw new InvalidTouristAttractionException();
        }
        if(name==null || name.isEmpty() || description==null || description.isEmpty()  || duration==null || duration.isEmpty()|| image==null)
        {
            throw new IllegalArgumentException();
        }
        Route route=new Route(name,description,duration,image,touristAttractions);
        return Optional.of(routeRepository.save(route));
    }

    @Override
    public Optional<Route> edit(Long id, String name,String description,String duration,String image,List<Long> touristAttractionsIds) {
        Route route=routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException(id));
        List<TouristAttraction> touristAttractions=touristAttractionRepository.findAllById(touristAttractionsIds);
        if(touristAttractions==null || touristAttractions.isEmpty())
        {
            throw new InvalidTouristAttractionException();
        }
        //route.setName(name);
        //route.setDescription(description);
        //route.setDuration(duration);
        //route.setTouristAttractions(touristAttractions);
        return Optional.of(routeRepository.save(route));
    }

    @Override
    public Optional<Route> setLikes(Long id) {
        Route route=routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException(id));
        //route.setLikes(route.getLikes()+1);
        return Optional.of(routeRepository.save(route));
    }

    @Override
    public void deleteById(Long id) {
        Route route=routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException(id));
        routeRepository.delete(route);
    }
}
