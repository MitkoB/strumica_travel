package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.TouristAttraction;

import java.util.List;
import java.util.Optional;

public interface TouristAttractionService {
   List<TouristAttraction> findAll();
   Optional<TouristAttraction> findById(Long id);
   Optional<TouristAttraction> findByName(String name);
   Optional<TouristAttraction> save(String name, String location,String description,String mainPicture,String pictures);
   Optional<TouristAttraction> edit(Long id, String name, String location, String description,String mainPicture, String pictures);
   void deleteById(Long id);
}
