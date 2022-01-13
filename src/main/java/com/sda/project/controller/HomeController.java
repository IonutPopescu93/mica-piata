package com.sda.project.controller;

import com.sda.project.dto.ProductDto;
import com.sda.project.model.Product;
import com.sda.project.service.ProductService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;

    private final ProductService productService;

    @Autowired
    public HomeController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping({"/index"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping({"/", "/home"})
    public String getHomePage(Model model) {
        model.addAttribute("productsDto", productService.findAll());
        return "home";
    }

    @GetMapping ("/search")
    public String getSearchPage (@Param ("value") String value, Model model) {
        System.out.println("value" + value);
        List<ProductDto> products = productService.searchProductByNameLike(value);
        model.addAttribute("value", value);
        model.addAttribute("products", products);
        return "fragments/search-result";
    }

    @GetMapping("/about")
    public String getAboutMePage(){
        return "user/about-me";
    }

    @GetMapping("/contact")
    public String getContactPage(){
        return "user/contact";
    }

}
