package com.sda.project.controller;

import com.sda.project.dto.UserDto;
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
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin/dashboard";
    }

    @GetMapping("/admin/users/user-add")
    public String getRegisterPage(Model model) {
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
    public String getProductListPage(Model model) {
        model.addAttribute("usersDto", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/admin/users/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        UserDto userToUpdate = userService.findById(id);
        model.addAttribute("userDto", userToUpdate);
        return "admin/user-edit";
    }

    @PostMapping("/admin/users/{id}/edit")
    public String update(@PathVariable Long id,
                         @ModelAttribute UserDto userDto) {
        userService.update(userDto);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/delete")
    public String delete(Model model, @PathVariable Long id) {
        try {
            userService.delete(id);
            return "redirect:/admin/users";
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "admin/users";
        }
    }
}
