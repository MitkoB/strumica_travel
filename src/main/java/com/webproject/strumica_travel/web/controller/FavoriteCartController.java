package com.webproject.strumica_travel.web.controller;

import com.webproject.strumica_travel.model.FavoriteCart;
import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.service.FavoriteCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/favorite-cart")
public class FavoriteCartController {
    private final FavoriteCartService favoriteCartService;

    public FavoriteCartController(FavoriteCartService favoriteCartService) {
        this.favoriteCartService = favoriteCartService;
    }

    @GetMapping()
    public String getFavoriteCartPage(@RequestParam(required = false) String error,Model model,HttpServletRequest request)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasErrors",true);
            model.addAttribute("error",error);
        }
        FavoriteCart favoriteCart=favoriteCartService.getActiveFavoriteCart(request.getRemoteUser());
        model.addAttribute("attractions",favoriteCartService.listAllTouristAttractionsInFavoriteCart(favoriteCart.getId()));
        model.addAttribute("routes",favoriteCartService.listAllRoutesInFavoriteCart(favoriteCart.getId()));
        model.addAttribute("bodyContent","favorite-cart");
        return "master-template";
    }
    @PostMapping("/{id}/attractionAdd")
    public String addAttractionToFavoriteCart(@PathVariable Long id, Authentication authentication)
    {
        try{
            User user = (User) authentication.getPrincipal();
            favoriteCartService.addTouristAttractionsToFavoriteCart(user.getUsername(),id);
            return "redirect:/favorite-cart";
        }
        catch (RuntimeException ex)
        {
            return "redirect:/favorite-cart?error="+ex.getMessage();
        }
    }
    @PostMapping("/{id}/routeAdd")
    public String addRouteToFavoriteCart(@PathVariable Long id,Authentication authentication)
    {
        try{
            User user = (User) authentication.getPrincipal();
            favoriteCartService.addRouteToFavoriteCart(user.getUsername(),id);
            return "redirect:/favorite-cart";
        }
        catch (RuntimeException ex)
        {
            return "redirect:/favorite-cart?error="+ex.getMessage();
        }
    }
    @DeleteMapping("/{id}/remove-attr")
    public String removeAttractionFromCart(@PathVariable Long id,Authentication authentication)
    {
        try{
            User user = (User) authentication.getPrincipal();
            favoriteCartService.deleteTouristAttractionFromFavoriteCart(user.getUsername(),id);
            return "redirect:/favorite-cart";
        }
        catch (RuntimeException ex)
        {
            return "redirect:/favorite-cart?error="+ex.getMessage();
        }
    }
    @DeleteMapping("/{id}/remove-route")
    public String removeRoueFromCart(@PathVariable Long id,Authentication authentication)
    {
        try{
            User user = (User) authentication.getPrincipal();
            favoriteCartService.deleteRouteFromFavoriteCart(user.getUsername(),id);
            return "redirect:/favorite-cart";
        }
        catch (RuntimeException ex)
        {
            return "redirect:/favorite-cart?error="+ex.getMessage();
        }
    }

}
