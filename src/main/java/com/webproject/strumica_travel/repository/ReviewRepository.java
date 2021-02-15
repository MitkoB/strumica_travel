package com.webproject.strumica_travel.repository;

import com.webproject.strumica_travel.model.Review;
import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByTouristAttraction(TouristAttraction touristAttraction);
    List<Review> findAllByRoute(Route route);
}
