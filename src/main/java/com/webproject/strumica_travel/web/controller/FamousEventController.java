package com.webproject.strumica_travel.web.controller;
import com.webproject.strumica_travel.model.FamousEvent;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.service.FamousEventService;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/famousevents")
public class FamousEventController {
    private final FamousEventService famousEventService;
    private final TouristAttractionService touristAttractionService;
    public FamousEventController(FamousEventService famousEventService, TouristAttractionService touristAttractionService) {
        this.famousEventService = famousEventService;
        this.touristAttractionService = touristAttractionService;
    }
    @GetMapping
    public String getFamousEventPage(Model model)
    {
        List<FamousEvent> famousEvents=famousEventService.findAll();
        model.addAttribute("famousEvents",famousEvents);
        model.addAttribute("bodyContent","all_famousEvents");
        return "master-template";
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteFamousEvent(@PathVariable Long id)
    {
        famousEventService.deleteById(id);
        return "redirect:/famousevents";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddFamousEventPage(Model model)
    {
        List<TouristAttraction> touristAttractions=touristAttractionService.findAll();
        model.addAttribute("attractions",touristAttractions);
        model.addAttribute("bodyContent", "add-famousEvent");
        return "master-template";
    }

    @PostMapping("/save")
    public String saveFamousEvent(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String start,
            @RequestParam String finish,
            @RequestParam String picture,
            @RequestParam String location )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start1 = LocalDateTime.parse(start, formatter);
        LocalDateTime finish1 = LocalDateTime.parse(finish, formatter);
        this.famousEventService.save(title,description,start1,finish1,picture,location);
        return "redirect:/famousevents";
    }
    @GetMapping("/{id}/edit-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditFamousEventPage(@PathVariable Long id,Model model)
    {
        if(this.famousEventService.findById(id).isPresent())
        {
            FamousEvent famousEvent=this.famousEventService.findById(id).get();
            List<TouristAttraction> touristAttractions=touristAttractionService.findAll();
            model.addAttribute("attractions",touristAttractions);
            model.addAttribute("famousEvent",famousEvent);
            model.addAttribute("bodyContent", "add-famousEvent");
            return "master-template";
        }
        return "redirect:/famous-events";
    }

    @PostMapping("/save/{id}")
    public String updateFamousEvent(@PathVariable Long id,
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam String start,
                              @RequestParam String finish,
                              @RequestParam String picture,
                              @RequestParam String location ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start1 = LocalDateTime.parse(start, formatter);
        LocalDateTime finish1 = LocalDateTime.parse(finish, formatter);
       this.famousEventService.edit(id,title,description,start1,finish1,picture,location);
        return "redirect:/famousevents";
    }

    @GetMapping("/{id}/details")
    public String getFamousEventDetailsPage(@PathVariable Long id,Model model)
    {
        if(this.famousEventService.findById(id).isPresent())
        {
            FamousEvent famousEvent=this.famousEventService.findById(id).get();
            model.addAttribute("famousEvent",famousEvent);
            model.addAttribute("bodyContent", "famousEvent_details");
            return "master-template";
        }
        return "redirect:/famousevents";
    }
}
