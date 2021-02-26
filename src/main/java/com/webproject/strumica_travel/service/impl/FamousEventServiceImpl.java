package com.webproject.strumica_travel.service.impl;

import com.webproject.strumica_travel.model.FamousEvent;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.enumeration.Season;
import com.webproject.strumica_travel.model.exception.FamousEventNotFoundException;
import com.webproject.strumica_travel.model.exception.InvalidTouristAttractionException;
import com.webproject.strumica_travel.repository.FamousEventRepository;
import com.webproject.strumica_travel.repository.TouristAttractionRepository;
import com.webproject.strumica_travel.service.FamousEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FamousEventServiceImpl implements FamousEventService {
    private final FamousEventRepository famousEventRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    public FamousEventServiceImpl(FamousEventRepository famousEventRepository, TouristAttractionRepository touristAttractionRepository) {
        this.famousEventRepository = famousEventRepository;
        this.touristAttractionRepository = touristAttractionRepository;
    }

    @Override
    public List<FamousEvent> findAll() {
        return famousEventRepository.findAll();
    }

    @Override
    public Optional<FamousEvent> findById(Long id) {
        return famousEventRepository.findById(id);
    }

    @Override
    public Optional<FamousEvent> findByTitle(String title) {
        return famousEventRepository.findByTitle(title);
    }

    @Override
    public Optional<FamousEvent> save(String title, String description, LocalDateTime start, LocalDateTime end, String picture, String location) {
       // TouristAttraction touristAttraction=touristAttractionRepository.findById(touristAttractionsId).orElseThrow(()->new InvalidTouristAttractionException());

        if(title==null || title.isEmpty() || description==null || description.isEmpty()  || picture==null || picture.isEmpty()  || location==null || location.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        FamousEvent famousEvent=new FamousEvent(title,description,start,end,picture,location);
        return Optional.of(famousEventRepository.save(famousEvent));
    }

    @Override
    public Optional<FamousEvent> edit(Long id, String title, String description, LocalDateTime start, LocalDateTime end, String picture, String location) {
        FamousEvent famousEvent=famousEventRepository.findById(id).orElseThrow(()->new FamousEventNotFoundException());
       // TouristAttraction touristAttraction=touristAttractionRepository.findById(touristAttractionsId).orElseThrow(()->new InvalidTouristAttractionException());
        famousEvent.setTitle(title);
        famousEvent.setDescription(description);
        famousEvent.setStart(start);
        famousEvent.setEnd(end);
        famousEvent.setPicture(picture);
        famousEvent.setLocation(location);
        return Optional.of(famousEventRepository.save(famousEvent));
    }

    @Override
    public void deleteById(Long id) {
        FamousEvent famousEvent=famousEventRepository.findById(id).orElseThrow(()->new FamousEventNotFoundException());
        famousEventRepository.delete(famousEvent);
    }
}
