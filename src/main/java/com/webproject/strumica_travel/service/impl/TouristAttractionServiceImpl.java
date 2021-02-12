package com.webproject.strumica_travel.service.impl;

import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.exception.TouristAttractionNotFoundException;
import com.webproject.strumica_travel.repository.TouristAttractionRepository;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristAttractionServiceImpl implements TouristAttractionService {
    private final TouristAttractionRepository touristAttractionRepository;

    public TouristAttractionServiceImpl(TouristAttractionRepository touristAttractionRepository) {
        this.touristAttractionRepository = touristAttractionRepository;
    }

    @Override
    public List<TouristAttraction> findAll() {
        return touristAttractionRepository.findAll();
    }

    @Override
    public Optional<TouristAttraction> findById(Long id) {
        return touristAttractionRepository.findById(id);
    }

    @Override
    public Optional<TouristAttraction> findByName(String name) {
        return touristAttractionRepository.findByName(name);
    }

    @Override
    public Optional<TouristAttraction> save(String name, String location, String description, String pictures) {
        if(name==null || name.isEmpty() || location==null || location.isEmpty() || description==null || description.isEmpty() || pictures!=null || pictures.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        TouristAttraction touristAttraction=new TouristAttraction(name,location,description,pictures);
        return Optional.of(touristAttractionRepository.save(touristAttraction));
    }

    @Override
    public Optional<TouristAttraction> edit(Long id, String name, String location, String description, String pictures) {
        TouristAttraction touristAttraction=touristAttractionRepository.findById(id).orElseThrow(()->new TouristAttractionNotFoundException(id));
        touristAttraction.setName(name);
        touristAttraction.setLocation(location);
        touristAttraction.setDescription(description);
        touristAttraction.setPictures(pictures);
        return Optional.of(this.touristAttractionRepository.save(touristAttraction));
    }

    @Override
    public void deleteById(Long id) {
        TouristAttraction touristAttraction=touristAttractionRepository.findById(id).orElseThrow(()->new TouristAttractionNotFoundException(id));
        touristAttractionRepository.delete(touristAttraction);
    }
}
