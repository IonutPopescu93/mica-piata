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

    @GetMapping("/admin/products")
    public String getProductListPage(Model model) {
        model.addAttribute("productsDto", productService.findAll());
        return "product/products";
    }

    @GetMapping("/admin/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "product/product-add";
    }

    @PostMapping("/admin/products/add")
    public String save(@ModelAttribute("productDto") ProductDto productDto) {
        productService.save(productDto);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        ProductDto productToUpdate = productService.findById(id);
        model.addAttribute("productDto", productToUpdate);
        return "product/edit-product";
    }

    @PostMapping("/admin/products/{id}/edit")
    public String update(@PathVariable Long id,
                         @ModelAttribute ProductDto productDto) {
        productService.update(productDto);
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    private String showAllProducts(Model model) {
        model.addAttribute("productsDto", productService.findAll());
        return "product/products-list";
    }

    @GetMapping("/product/{id}")
    private String showProductDetail(Model model, @PathVariable Long id) {
        model.addAttribute("productsDto", productService.findById(id));
        return "product/product-view";
    }

}

