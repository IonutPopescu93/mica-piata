package com.sda.project.controller;

import com.sda.project.dto.ProductDto;
import com.sda.project.dto.UserDto;
import com.sda.project.service.ProductService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final UserService userService;

    private final ProductService productService;

    @Autowired
    public AdminController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin/dashboard";
    }

    @GetMapping("/admin/users/user-add")
    public String register(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        // folder / page name
        return "admin/user-add";
    }

    @PostMapping("/admin/users/add")
    public String register(@ModelAttribute("userDto") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String getUsersListPage(Model model) {
        model.addAttribute("usersDto", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/admin/users/{id}")
    public String updateUser(Model model, @PathVariable Long id) {
        UserDto userToUpdate = userService.findById(id);
        model.addAttribute("userDto", userToUpdate);
        return "admin/user-edit";
    }

    @PostMapping("/admin/users/{id}/edit")
    public String updateUser(@PathVariable Long id,
                         @ModelAttribute UserDto userDto) {
        userService.update(userDto);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/delete")
    public String deleteUser(Model model, @PathVariable Long id) {
        try {
            userService.delete(id);
            return "redirect:/admin/users";
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "admin/users";
        }
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
    public String showProductEditForm(Model model, @PathVariable Long id) {
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

    @GetMapping("/admin/products/{id}/delete")
    public String deleteProduct(Model model, @PathVariable Long id) {
        try {
            productService.delete(id);
            return "redirect:/admin/products";
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "admin/products";
        }
    }
}
