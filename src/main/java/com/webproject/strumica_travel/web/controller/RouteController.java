package com.webproject.strumica_travel.web.controller;

import com.webproject.strumica_travel.model.Review;
import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.service.ReviewService;
import com.webproject.strumica_travel.service.RouteService;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final TouristAttractionService touristAttractionService;
    private final ReviewService reviewService;
    public RouteController(RouteService routeService, TouristAttractionService touristAttractionService, ReviewService reviewService) {
        this.routeService = routeService;
        this.touristAttractionService = touristAttractionService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getRoutesPage(@RequestParam(required = false) String nameSearch, Model model)
    {
        List<Route> routes;
        if(nameSearch!=null)
        {
            routes=routeService.searchRoute(nameSearch);
        }
        else {
            routes=routeService.findAll();
        }
        model.addAttribute("routes",routes);
        model.addAttribute("bodyContent","all_routes");
        return "master-template";
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRoute(@PathVariable Long id)
    {
        routeService.deleteById(id);
        return "redirect:/routes";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddRoutesPage(Model model)
    {
        List<TouristAttraction> touristAttractions=touristAttractionService.findAll();
        model.addAttribute("attractions",touristAttractions);
        model.addAttribute("bodyContent", "add-route");
        return "master-template";
    }

    @PostMapping("/save")
    public String saveRoute(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String duration,
            @RequestParam String image,
            @RequestParam List<Long> attractionsIds )
    {
        this.routeService.save(name,description,duration,image,attractionsIds);
        return "redirect:/routes";
    }
    @GetMapping("/{id}/edit-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditRoutePage(@PathVariable Long id,Model model)
    {
        if(this.routeService.findById(id).isPresent())
        {
            Route route=this.routeService.findById(id).get();
            List<TouristAttraction> touristAttractions=touristAttractionService.findAll();
            model.addAttribute("attractions",touristAttractions);
            model.addAttribute("route",route);
            model.addAttribute("bodyContent", "add-route");
            return "master-template";
        }
        return "redirect:/routes";
    }

    @PostMapping("/save/{id}")
    public String updateRoute(@PathVariable Long id,
                                   @RequestParam String name,
                                   @RequestParam String description,
                                   @RequestParam String duration,
                                   @RequestParam String image,
                                   @RequestParam List<Long> attractionsIds) {
        this.routeService.edit(id, name, description, duration,image,attractionsIds);
        return "redirect:/routes";
    }

    @GetMapping("/{id}/details")
    public String getRoutesDetailsPage(@PathVariable Long id,Model model)
    {
        if(this.routeService.findById(id).isPresent())
        {
            Route route=this.routeService.findById(id).get();
            model.addAttribute("route",route);
            List<Review> reviews=this.reviewService.listAllRouteReviews(id);
            List<TouristAttraction> attractions=route.getTouristAttractions();
            int sizeOf1=reviewService.numberOfReviewsInRoutesByGrade(id,1);
            int sizeOf2=reviewService.numberOfReviewsInRoutesByGrade(id,2);
            int sizeOf3=reviewService.numberOfReviewsInRoutesByGrade(id,3);
            int sizeOf4=reviewService.numberOfReviewsInRoutesByGrade(id,4);
            int sizeOf5=reviewService.numberOfReviewsInRoutesByGrade(id,5);
            model.addAttribute("sizeOf1",sizeOf1);
            model.addAttribute("sizeOf2",sizeOf2);
            model.addAttribute("sizeOf3",sizeOf3);
            model.addAttribute("sizeOf4",sizeOf4);
            model.addAttribute("sizeOf5",sizeOf5);
            model.addAttribute("reviews",reviews);
            model.addAttribute("bodyContent", "route_details");
            model.addAttribute("attractions",attractions);
            return "master-template";
        }
        return "redirect:/routes";
    }
    @GetMapping("/{id}/like")
    public String setLike(@PathVariable Long id)
    {
        if(this.routeService.findById(id).isPresent())
        {
            routeService.setLikes(id);
            return "redirect:/routes";
        }
        return "redirect:/routes";
    }
    @PostMapping("/{id}/add-review")
    public String addReview(@PathVariable Long id,
                            @RequestParam String comment,
                            @RequestParam String grade, HttpServletRequest request)
    {
        Integer grade1=Integer.parseInt(grade);
        this.reviewService.addReviewForRoute(request.getRemoteUser(),id,comment,grade1);
        return "redirect:/routes/"+id+"/details";

    }
    @DeleteMapping("/{id}/delete-review")
    public String deleteRouteReview(@PathVariable Long id,@RequestParam(required = false) Long routeId, HttpServletRequest request)
    {
        reviewService.deleteReview(request.getRemoteUser(),id);
        return "redirect:/routes/"+routeId+"/details";
    }
}
