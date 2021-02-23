package com.webproject.strumica_travel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping(value = {"/","/home"})
    public String getHomePage(Model model)
    {
        model.addAttribute("bodyContent","home");
        return "master-template";
    }

    @GetMapping(value ={"/learnMore"})
    public String getTraditionalFoodPage(Model model,@RequestParam(required = false) String jumpSection){
        model.addAttribute("bodyContent","learnMore");
        model.addAttribute("jumpSection",jumpSection);
        return "master-template";
    }
}
