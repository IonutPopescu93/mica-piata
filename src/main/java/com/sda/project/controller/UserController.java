package com.sda.project.controller;

import com.sda.project.dto.ChangePasswordDto;
import com.sda.project.dto.CreateUser;
import com.sda.project.service.UserService;
import com.sda.project.validator.ChangePasswordValidator;
import com.sda.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ChangePasswordValidator changePasswordValidator;

    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model) {
        CreateUser createUser = new CreateUser();
        model.addAttribute("userDto", createUser);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistrationPage(@ModelAttribute CreateUser createUser, BindingResult bindingResult) {
        System.out.println("S-a apelat postRegistrationPage!!!");
        System.out.println(createUser);
        userValidator.validate(createUser, bindingResult);
        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.save(createUser);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "changePassword";
    }

    @PostMapping("/change-password")
    public String postChangePassword(@ModelAttribute(value = "changePasswordDto") ChangePasswordDto changePasswordDto,
                                     BindingResult bindingResult,
                                     Authentication authentication) {
        System.out.println(authentication.getName());
        changePasswordValidator.validate(changePasswordDto, bindingResult, authentication.getName());
        if (bindingResult.hasErrors()) {
            return "changePassword";
        }
        userService.changePassword(changePasswordDto, authentication.getName());
        return "redirect:/login";
    }
}
