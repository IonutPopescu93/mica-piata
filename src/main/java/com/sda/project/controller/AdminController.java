package com.sda.project.controller;

import com.sda.project.config.FileUploadUtil;
import com.sda.project.dto.OrderDto;
import com.sda.project.dto.ProductDto;
import com.sda.project.dto.UserDto;
import com.sda.project.service.OrderService;
import com.sda.project.service.ProductService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AdminController {

    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    @Autowired
    public AdminController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("ordersDto", orderService.findAll());
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
        return "admin/products";
    }
    @GetMapping("/admin/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "admin/product-add";
    }

    @PostMapping("/admin/products/add")
    public String addPetForm(ProductDto productDto,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        productDto.setPhoto(fileName);
        ProductDto savedProductDto = productService.save(productDto);


        String uploadDir = "product-photos/" + savedProductDto.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{id}")
    public String showProductEditForm(Model model, @PathVariable Long id) {
        ProductDto productToUpdate = productService.findById(id);
        model.addAttribute("productDto", productToUpdate);
        return "admin/product-edit";
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
            return "redirect:/admin/products";
        }
    }
}
