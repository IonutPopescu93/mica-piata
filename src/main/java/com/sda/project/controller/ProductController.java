package com.sda.project.controller;

import com.sda.project.dto.ProductDto;
import com.sda.project.repository.ProductRepository;
import com.sda.project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping(value = "/product-add")
    public String showAddForm(Model model, ProductDto productDto) {
        model.addAttribute("productDto", productDto);
        return "/product/product-add";
    }

    @PostMapping(value = "/product-add")
    public String save(Model model, @ModelAttribute("productDto") ProductDto productDto) {
        productService.save(productDto);
        model.addAttribute("productDto", productDto);
        return "redirect:/product-add";

    }

    @GetMapping(value = "/products")
    public String getProductListPage(Model model) {
        return "/product/products-list";
    }
}

