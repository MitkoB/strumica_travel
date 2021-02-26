package com.webproject.strumica_travel.web.rest;
import com.webproject.strumica_travel.model.FamousEvent;
import com.webproject.strumica_travel.model.Review;
import com.webproject.strumica_travel.service.FamousEventService;
import com.webproject.strumica_travel.service.ReviewService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FamousEventRestController {
    private final FamousEventService famousEventService;
    private final ReviewService reviewService;
    public FamousEventRestController(FamousEventService famousEventService, ReviewService reviewService) {
        this.famousEventService = famousEventService;
        this.reviewService = reviewService;
    }

    @RequestMapping(value="/allevents", method= RequestMethod.GET)
    public List<FamousEvent> allEvents() {
        return famousEventService.findAll();
    }
//    @RequestMapping(value="/allreviews/{id}", method= RequestMethod.GET)
//    public List<Review> allEvents(@PathVariable Long id) {
//        return reviewService.listAllRouteReviews(id);
//    }
}
