package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.FamousEvent;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FamousEventService {
    List<FamousEvent> findAll();
    Optional<FamousEvent> findById(Long id);
    Optional<FamousEvent> findByTitle(String title);
    Optional<FamousEvent> save(String title, String description, LocalDateTime start, LocalDateTime end, String picture, String location);
    Optional<FamousEvent> edit(Long id,String title, String description, LocalDateTime start, LocalDateTime end, String picture, String location);
    void deleteById(Long id);
}
