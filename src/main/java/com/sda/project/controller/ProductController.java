package com.sda.project.controller;

import com.sda.project.dto.ProductDto;
import com.sda.project.model.Category;
import com.sda.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    private String showAllProducts(Model model) {
        model.addAttribute("productDto", productService.findAll());
        return "product/products-list";
    }

    @GetMapping("/products/{category}/{id}")
    public String showSelectedProduct(Model model, @PathVariable Category category, @PathVariable Long id) {
        ProductDto product = productService.findById(id);
        model.addAttribute("productDto", product);
        return "product/product-view";
    }
}

