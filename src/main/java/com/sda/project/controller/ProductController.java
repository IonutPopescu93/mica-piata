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

    @GetMapping(value = "/addProduct")
    public String getAddProductPage(Model model) {
        return "/product/addProduct";
    }

    @PostMapping(value = "/addProduct")
    public String postAddProductPage(@ModelAttribute(value = "productDto") ProductDto productDto) {
        productService.addProduct(productDto);
        return "redirect:/addProduct";
    }

    @GetMapping(value = "/listProduct")
    public String getProductListPage(Model model) {
        model.addAttribute("product", productService.findAll());
        return "/product/product-list";
    }
}
