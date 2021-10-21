package com.sda.project.controller;

import com.sda.project.dto.UserDto;
import com.sda.project.service.UserService;
import com.sda.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "redirect:/registration";
    }

    @GetMapping(value = "/home")
    public String getHomePage() {
        return "home";
    }


}
