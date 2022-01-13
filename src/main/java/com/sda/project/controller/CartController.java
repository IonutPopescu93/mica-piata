package com.sda.project.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.sda.project.model.Product;
import com.sda.project.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/addToCart")
    public String addToCart(HttpServletRequest request, Model model, @RequestParam("id") Long id,
                            @RequestParam("quantity") int quantity, Product product) {

        // sessiontToken
        String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
        if (sessionToken == null) {

            sessionToken = UUID.randomUUID().toString();
            request.getSession().setAttribute("sessiontToken", sessionToken);
            shoppingCartService.addShoppingCartFirstTime(id, sessionToken, quantity, product);
        } else {
            shoppingCartService.addToExistingShoppingCart(id, sessionToken, quantity, product);
        }
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String showShoopingCartView(HttpServletRequest request, Model model) {

        return "cart/shoppingCart";
    }

    @PostMapping("/updateShoppingCart")
    public String updateCartItem(@RequestParam("item_id") Long id,
                                 @RequestParam("quantity") int quantity) {

        shoppingCartService.updateShoppingCartItem(id,quantity);
        return "redirect:shoppingCart";
    }
    @GetMapping("/removeCartItem/{id}")
    public String removeItem(@PathVariable("id") Long id, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
        System.out.println("got here ");
        shoppingCartService.removeCartIemFromShoppingCart(id,sessionToken);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/clearShoppingCart")
    public String clearShoopiString(HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
        request.getSession(false).removeAttribute("sessiontToken");
        shoppingCartService.clearShoppingCart(sessionToken);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(){
        return "cart/checkout";
    }
}

