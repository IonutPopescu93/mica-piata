package com.sda.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @GetMapping ("/checkout")
    public String getCheckoutPage(){
        return "cart/checkout";
    }

    @GetMapping("/payment")
    public String getPaymentPage(){
        return "cart/payment";
    }
}
