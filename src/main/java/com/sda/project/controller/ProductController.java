package com.sda.project.controller;

import com.sda.project.dto.ProductDto;
import com.sda.project.repository.ProductRepository;
import com.sda.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/addProduct")
    public String getAddProductPage(Model model) {
        return "/product/addProduct";
    }

    @PostMapping(value = "/addProduct")
    public String postAddProductPage(@ModelAttribute(value = "productDto") ProductDto productDto) {
        productService.addProduct(productDto);
        return "redirect:/addProduct";
    }
}
