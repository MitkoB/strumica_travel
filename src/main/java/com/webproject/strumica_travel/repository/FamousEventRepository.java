package com.webproject.strumica_travel.repository;

import com.webproject.strumica_travel.model.FamousEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FamousEventRepository extends JpaRepository<FamousEvent,Long> {
     Optional<FamousEvent> findByTitle(String title);
     List<FamousEvent> findByStartGreaterThanEqualAndEndLessThanEqual(LocalDateTime start, LocalDateTime end);
}
