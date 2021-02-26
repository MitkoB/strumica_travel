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

import java.util.List;

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
                           @RequestParam String surname
                           )
    {
        User user;
        try
        {
            user=userService.register(username,password,repeatedPassword,name,surname);
            return "redirect:/login";
        }
        catch (PasswordsDoNotMatchException | IllegalArgumentException exception)
        {
            return "redirect:/register?error="+exception.getMessage();
        }
    }
    @GetMapping("/add-role-to-user")
    public String getAddRoleToUserPage(@RequestParam(required=false) String error,Model model)
    {
        if(error!=null && error.isEmpty())
        {
            model.addAttribute("hasErrors",true);
            model.addAttribute("error",error);
        }
        List<User> users=userService.findAll();
        model.addAttribute("users",users);
        model.addAttribute("bodyContent","add-user-to-role");
        return "master-template";
    }
    @PostMapping("/add-role-to-user")
    public String AddRoleToUser(@RequestParam String username, @RequestParam Role role,Model model)
    {
        User user;
        try{
            user=this.userService.addRoleToUser(username,role);
            model.addAttribute("usernameselected",username);
            model.addAttribute("roleselected",String.valueOf(role));
            List<User> users=userService.findAll();
            model.addAttribute("users",users);
            model.addAttribute("bodyContent","add-user-to-role");
            return "master-template";
        }
        catch (RuntimeException ex)
        {
            return "redirect:/register/add-role-to-user?error="+ex.getMessage();
        }

    }

}
