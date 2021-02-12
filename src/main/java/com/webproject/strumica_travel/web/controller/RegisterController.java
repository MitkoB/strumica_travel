package com.webproject.strumica_travel.web.controller;

import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.model.enumeration.Role;
import com.webproject.strumica_travel.model.exception.PasswordsDoNotMatchException;
import com.webproject.strumica_travel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required=false) String error, Model model)
    {
        if(error!=null && error.isEmpty())
        {
            model.addAttribute("hasErrors",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }
    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role)
    {
        User user;
        try
        {
            user=userService.register(username,password,repeatedPassword,name,surname,role);
            return "redirect:/login";
        }
        catch (PasswordsDoNotMatchException | IllegalArgumentException exception)
        {
            return "redirect:/register?error="+exception.getMessage();
        }
    }
}
