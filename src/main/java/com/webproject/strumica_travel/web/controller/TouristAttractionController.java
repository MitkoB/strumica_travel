package com.webproject.strumica_travel.web.controller;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {
    private final TouristAttractionService touristAttractionService;
    public TouristAttractionController(TouristAttractionService touristAttractionService) {
        this.touristAttractionService = touristAttractionService;
    }
    @GetMapping
    public String getAttractionsPage(Model model)
    {
        List<TouristAttraction> touristAttractionList=this.touristAttractionService.findAll();
        model.addAttribute("attractions",touristAttractionList);
        model.addAttribute("bodyContent","tourist_attractions");
        return "master-template";
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable Long id)
    {
        touristAttractionService.deleteById(id);
        return "redirect:/attractions";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddAttractionPage(Model model)
    {
        model.addAttribute("bodyContent", "add-attraction");
        return "master-template";
    }
    @PostMapping("/save")
    public String saveAttraction(
                              @RequestParam String name,
                              @RequestParam String location,
                              @RequestParam String description,
                              @RequestParam String pictures)
    {
        this.touristAttractionService.save(name,location,description,pictures);
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
             model.addAttribute("bodyContent", "add-attraction");
             return "master-template";

        }
        return "redirect:/attractions";
    }
    @PostMapping("/save/{id}")
    public String updateAttraction(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String location,
                         @RequestParam String description,
                         @RequestParam String pictures) {
        this.touristAttractionService.edit(id, name, location, description,pictures);
        return "redirect:/attractions";
    }
    @GetMapping("/{id}/details")
    public String getDetailsAttractionPage(@PathVariable Long id,Model model)
    {
        if(this.touristAttractionService.findById(id).isPresent())
        {
            TouristAttraction touristAttraction=this.touristAttractionService.findById(id).get();
            model.addAttribute("attraction",touristAttraction);
            model.addAttribute("bodyContent", "attraction_details");
            return "master-template";

        }
        return "redirect:/attractions";
    }

}
