package com.webproject.strumica_travel.repository;

import com.webproject.strumica_travel.model.FavoriteCart;
import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.model.enumeration.FavoriteCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteCartRepository extends JpaRepository<FavoriteCart,Long> {
    Optional<FavoriteCart> findByUserAndFavoriteCartStatus(User user, FavoriteCartStatus status);
}
