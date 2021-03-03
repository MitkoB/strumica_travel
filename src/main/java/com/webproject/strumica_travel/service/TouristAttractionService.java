package com.webproject.strumica_travel.service;


import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.enumeration.AttractionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TouristAttractionService {
   List<TouristAttraction> findAll();
   Page<TouristAttraction> findPaginated(Pageable pageable);
   Page<TouristAttraction> findPaginated(Pageable pageable,AttractionType type);
   Page<TouristAttraction> findPaginated(Pageable pageable, String nameSearch);
   Optional<TouristAttraction> findById(Long id);
   Optional<TouristAttraction> findByName(String name);
   Optional<TouristAttraction> save(String name, String location, String description, String mainPicture, String pictures, AttractionType type);
   Optional<TouristAttraction> edit(Long id, String name, String location, String description,String mainPicture, String pictures, AttractionType type);
   List<TouristAttraction> searchByName(String name);
   List<TouristAttraction> searchByType(AttractionType type);
   void deleteById(Long id);

}
