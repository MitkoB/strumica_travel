package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.enumeration.AttractionType;

import java.util.List;
import java.util.Optional;

public interface TouristAttractionService {
   List<TouristAttraction> findAll();
   Optional<TouristAttraction> findById(Long id);
   Optional<TouristAttraction> findByName(String name);
   Optional<TouristAttraction> save(String name, String location, String description, String mainPicture, String pictures, AttractionType type);
   Optional<TouristAttraction> edit(Long id, String name, String location, String description,String mainPicture, String pictures, AttractionType type);
   List<TouristAttraction> searchByName(String name);
   List<TouristAttraction> searchByType(AttractionType type);
   void deleteById(Long id);
}
