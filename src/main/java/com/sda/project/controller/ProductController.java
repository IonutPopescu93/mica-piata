package com.sda.project.controller;

import com.sda.project.dto.ProductDto;
import com.sda.project.model.Category;
import com.sda.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product/detail")
    private String showProductDetail() {

        return "product/product-view";
    }

    @GetMapping("/products")
    private String showAllProducts(Model model){
        model.addAttribute("productDto", productService.findAll());
        return "product/products-list";
    }

    @GetMapping("/products/detail/{id}")
    public String showProduct(Model model, @PathVariable Long id) {
        ProductDto product = productService.findById(id);
        model.addAttribute("productDto", product);
        return "product/product-view";
    }
    @GetMapping("/products/category")
    public String getCategoryPage (Model model, @PathVariable("category") String category) {
        System.out.println("value" + category);
        List<ProductDto> products = productService.findAllByCategory(category);
        model.addAttribute("value", category);
        model.addAttribute("products", products);
        return "product/products-category";
    }

}

