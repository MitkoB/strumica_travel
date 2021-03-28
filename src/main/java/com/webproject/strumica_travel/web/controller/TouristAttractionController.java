package com.webproject.strumica_travel.web.controller;
import com.webproject.strumica_travel.model.Review;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.enumeration.AttractionType;
import com.webproject.strumica_travel.service.ReviewService;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {

    private final TouristAttractionService touristAttractionService;
    private final ReviewService reviewService;
    public TouristAttractionController(TouristAttractionService touristAttractionService, ReviewService reviewService) {
        this.touristAttractionService = touristAttractionService;
        this.reviewService = reviewService;
    }
    @GetMapping
    public String getAttractionsPage(@RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size,
                                     @RequestParam(required = false) String nameSearch,
                                     @RequestParam(required = false) AttractionType typeSearch, Model model)
    {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<TouristAttraction> touristAttractionPage=touristAttractionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));;

        if(nameSearch!=null)
        {
            touristAttractionPage=touristAttractionService.findPaginated(PageRequest.of(currentPage - 1, pageSize),nameSearch);
        }
        else if(typeSearch!=null)
        {
            touristAttractionPage=touristAttractionService.findPaginated(PageRequest.of(currentPage - 1, pageSize),typeSearch);
        }

        int totalPages = touristAttractionPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        AttractionType[] attractionTypes=AttractionType.values();
        model.addAttribute("attractionTypes",attractionTypes);
        model.addAttribute("touristAttractionPage", touristAttractionPage);
        model.addAttribute("bodyContent","tourist_attractions");
        return "master-template";
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAttraction(@PathVariable Long id)
    {
        touristAttractionService.deleteById(id);
        return "redirect:/attractions";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddAttractionPage(Model model)
    {
        AttractionType[] attractionTypes=AttractionType.values();
        model.addAttribute("attractionTypes",attractionTypes);
        model.addAttribute("bodyContent", "add-attraction");
        return "master-template";
    }
    @PostMapping("/save")
    public String saveAttraction(
                              @RequestParam String name,
                              @RequestParam String location,
                              @RequestParam Double latitude,
                              @RequestParam Double longitude,
                              @RequestParam String description,
                              @RequestParam String mainPicture,
                              @RequestParam String pictures,
                              @RequestParam AttractionType type)
    {

        this.touristAttractionService.save(name,location,latitude,longitude,description,mainPicture,pictures,type);
        return "redirect:/attractions";
    }
    @GetMapping("/{id}/edit-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditAttractionPage(@PathVariable Long id,Model model)
    {
        if(this.touristAttractionService.findById(id).isPresent())
        {
             TouristAttraction touristAttraction=this.touristAttractionService.findById(id).get();
             model.addAttribute("attraction",touristAttraction);
            AttractionType[] attractionTypes=AttractionType.values();
            model.addAttribute("attractionTypes",attractionTypes);
             model.addAttribute("bodyContent", "add-attraction");
             return "master-template";

        }
        return "redirect:/attractions";
    }
    @PostMapping("/save/{id}")
    public String updateAttraction(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String location,
                         @RequestParam Double latitude,
                         @RequestParam Double longitude,
                         @RequestParam String description,
                         @RequestParam String mainPicture,
                         @RequestParam String pictures,
                                   @RequestParam AttractionType type) {
        this.touristAttractionService.edit(id, name, location,latitude,longitude, description,mainPicture,pictures,type);
        return "redirect:/attractions";
    }
    @GetMapping("/{id}/details")
    public String getDetailsAttractionPage(@PathVariable Long id,Model model)
    {
        if(this.touristAttractionService.findById(id).isPresent())
        {
            TouristAttraction touristAttraction=this.touristAttractionService.findById(id).get();
            List<Review> reviews=this.reviewService.listAllTouristAttractionReviews(id);
            int sizeOf1=reviewService.numberOfReviewsInAttractionsByGrade(id,1);
            int sizeOf2=reviewService.numberOfReviewsInAttractionsByGrade(id,2);
            int sizeOf3=reviewService.numberOfReviewsInAttractionsByGrade(id,3);
            int sizeOf4=reviewService.numberOfReviewsInAttractionsByGrade(id,4);
            int sizeOf5=reviewService.numberOfReviewsInAttractionsByGrade(id,5);
            model.addAttribute("sizeOf1",sizeOf1);
            model.addAttribute("sizeOf2",sizeOf2);
            model.addAttribute("sizeOf3",sizeOf3);
            model.addAttribute("sizeOf4",sizeOf4);
            model.addAttribute("sizeOf5",sizeOf5);
            model.addAttribute("reviews",reviews);
            model.addAttribute("attraction",touristAttraction);
            model.addAttribute("pictures",Arrays.asList(touristAttraction.getPictures().split(" ")));
            model.addAttribute("reviews",reviews);
            model.addAttribute("longitude",touristAttraction.getLongitude());
            model.addAttribute("latitude",touristAttraction.getLatitude());
            model.addAttribute("bodyContent", "attraction_details");
            return "master-template";
        }
        return "redirect:/attractions";
    }
    @PostMapping("/{id}/add-review")
    public String addReview(@PathVariable Long id,
                            @RequestParam String comment,
                            @RequestParam String grade, HttpServletRequest request,Model model)
    {
        Integer grade1=Integer.parseInt(grade);
        this.reviewService.addReviewForTouristAttraction(request.getRemoteUser(),id,comment,grade1);
        return "redirect:/attractions/"+id+"/details";


    }
    @DeleteMapping("/{id}/delete-review")
    public String deleteAttractionReview(@PathVariable Long id,@RequestParam(required = false) Long attractionId,HttpServletRequest request)
    {
        reviewService.deleteReview(request.getRemoteUser(),id);
        return "redirect:/attractions/"+attractionId+"/details";
    }

}
