package com.sda.project.controller;

import com.sda.project.dto.ProductDto;
import com.sda.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // show an empty dto object
    @GetMapping("/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "product/product-add";
    }

    // the dto contains all inputs from user
    @PostMapping("/products/add")
    public String save(@ModelAttribute("productDto") ProductDto productDto) {
        productService.save(productDto);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getProductListPage(Model model) {
        model.addAttribute("productsDto", productService.findAll());
        return "product/products";
    }

    @GetMapping("products/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        ProductDto productToUpdate = productService.findById(id);
        model.addAttribute("productDto", productToUpdate);
        return "product/edit-product";
    }

    @PostMapping("/products/{id}/edit")
    public String update(@PathVariable Long id,
                         @ModelAttribute ProductDto productDto) {
        productService.update(productDto);
        return "redirect:/products";
    }

    @GetMapping("product/detail")
    private String showProductDetail() {

        return "product/product-detail";
    }

}

