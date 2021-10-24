package com.sda.project.controller;

import com.sda.project.dto.ChangePasswordDto;
import com.sda.project.dto.UserDto;
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
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistrationPage(@ModelAttribute UserDto userDto, BindingResult bindingResult) {
        System.out.println("S-a apelat postRegistrationPage!!!");
        System.out.println(userDto);
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.addUser(userDto);
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "changePassword";
    }

    @PostMapping(value = "/changePassword")
    public String postChangePassword(@ModelAttribute(value = "changePasswordDto") ChangePasswordDto changePasswordDto, BindingResult bindingResult, Authentication authentication) {
        System.out.println(authentication.getName());
        changePasswordValidator.validate(changePasswordDto, bindingResult, authentication.getName());
        if (bindingResult.hasErrors()) {
            return "changePassword";
        }
        userService.changePassword(changePasswordDto, authentication.getName());
        return "redirect:/login";
    }


}
