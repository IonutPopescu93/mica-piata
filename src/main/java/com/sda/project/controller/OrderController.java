package com.sda.project.controller;

import com.sda.project.dto.OrderDto;
import com.sda.project.model.Order;
import com.sda.project.repository.ShoppingCartRepository;
import com.sda.project.service.OrderService;
import com.sda.project.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping ("/checkout")
    public String getCheckoutPage(Model model){
        model.addAttribute("orderDto", new OrderDto());
        return "cart/checkout";
    }

    @GetMapping("/payment")
    public String getPaymentPage(){
        return "cart/payment";
    }

    @PostMapping(value="/checkout")
    public String saveOrder(@ModelAttribute (value="orderDto") OrderDto orderDto, Authentication authentication, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
        orderService.save(orderDto, authentication.getName(),sessionToken);
        return "redirect:/payment";
    }
}
