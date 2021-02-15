package com.webproject.strumica_travel.service.impl;

import com.webproject.strumica_travel.model.Review;
import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.model.enumeration.Role;
import com.webproject.strumica_travel.model.exception.*;
import com.webproject.strumica_travel.repository.ReviewRepository;
import com.webproject.strumica_travel.repository.RouteRepository;
import com.webproject.strumica_travel.repository.TouristAttractionRepository;
import com.webproject.strumica_travel.repository.UserRepository;
import com.webproject.strumica_travel.service.ReviewService;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, TouristAttractionRepository touristAttractionRepository, RouteRepository routeRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.touristAttractionRepository = touristAttractionRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Review> listAllTouristAttractionReviews(Long attractionId) {
        TouristAttraction touristAttraction=touristAttractionRepository.findById(attractionId).orElseThrow(()->new TouristAttractionNotFoundException(attractionId));
        return reviewRepository.findAllByTouristAttraction(touristAttraction);
    }

    @Override
    public List<Review> listAllRouteReviews(Long routeId) {
        Route route=routeRepository.findById(routeId).orElseThrow(()->new RouteNotFoundException(routeId));
        return reviewRepository.findAllByRoute(route);
    }

    @Override
    public Review addReviewForTouristAttraction(String username, Long attractionId,String comment,Integer grade) {
        TouristAttraction touristAttraction=touristAttractionRepository.findById(attractionId).orElseThrow(()->new TouristAttractionNotFoundException(attractionId));
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException());
        if(comment==null || comment.isEmpty() || grade==null)
        {
            throw new IllegalArgumentException();
        }
        Review review=new Review(user,touristAttraction,comment,grade);
        return reviewRepository.save(review);
    }

    @Override
    public Review addReviewForRoute(String username, Long routeId,String comment,Integer grade) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException());
        Route route=routeRepository.findById(routeId).orElseThrow(()->new RouteNotFoundException(routeId));
        if(comment==null || comment.isEmpty() || grade==null)
        {
            throw new IllegalArgumentException();
        }
        Review review=new Review(user,route,comment,grade);
        return reviewRepository.save(review);
    }


    @Override
    public void deleteReview(String username, Long reviewId) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException());
        Review review=reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
        if(username.equals(review.getUser().getUsername()) || user.getRole().equals(Role.ROLE_ADMIN))
        {
            this.reviewRepository.delete(review);
        }
        else {
            throw  new PermisionDeniedException();
        }
    }
}
