package com.webproject.strumica_travel.repository;

import com.webproject.strumica_travel.model.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TouristAttractionRepository extends JpaRepository<TouristAttraction,Long> {
   Optional<TouristAttraction> findByName(String name);
}
