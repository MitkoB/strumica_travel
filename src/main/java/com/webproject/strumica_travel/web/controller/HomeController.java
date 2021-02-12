package com.webproject.strumica_travel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/","/home"})
    public String getHomePage(Model model)
    {
        model.addAttribute("bodyContent","home");
        return "master-template";
    }
}
