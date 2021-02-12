package com.webproject.strumica_travel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {
    @GetMapping
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "redirect:/home";
    }
}
