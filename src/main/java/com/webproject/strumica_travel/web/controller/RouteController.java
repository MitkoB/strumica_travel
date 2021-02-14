package com.webproject.strumica_travel.web.controller;

import com.webproject.strumica_travel.model.Route;
import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.service.RouteService;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final TouristAttractionService touristAttractionService;
    public RouteController(RouteService routeService, TouristAttractionService touristAttractionService) {
        this.routeService = routeService;
        this.touristAttractionService = touristAttractionService;
    }

    @GetMapping
    public String getRoutesPage(Model model)
    {
        List<Route> routes=routeService.findAll();
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
            @RequestParam List<Long> attractionsIds )
    {
        this.routeService.save(name,description,duration,attractionsIds);
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
                                   @RequestParam List<Long> attractionsIds) {
        this.routeService.edit(id, name, description, duration,attractionsIds);
        return "redirect:/routes";
    }

    @GetMapping("/{id}/details")
    public String getRoutesDetailsPage(@PathVariable Long id,Model model)
    {
        if(this.routeService.findById(id).isPresent())
        {
            Route route=this.routeService.findById(id).get();
            model.addAttribute("route",route);
            model.addAttribute("bodyContent", "route_details");
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
}
