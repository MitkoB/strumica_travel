package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> listAllTouristAttractionReviews(Long attractionId);
    List<Review> listAllRouteReviews(Long routeId);
    Review addReviewForTouristAttraction(String username,Long attractionId,String comment,Integer grade);
    Review addReviewForRoute(String username,Long routeId,String comment,Integer grade);
    void deleteReview(String username,Long reviewId);
    Integer numberOfReviewsInRoutesByGrade(Long routeId,Integer grade);
    Integer numberOfReviewsInAttractionsByGrade(Long attractionId,Integer grade);

}
