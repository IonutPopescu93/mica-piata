package com.sda.project.controller;

import com.sda.project.dto.UserDto;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistrationPage(@ModelAttribute UserDto userDto) {
        System.out.println("S-a apelat postRestrationPage!!!");
        System.out.println(userDto);
        userService.addUser(userDto);
        return "redirect:/registration";
    }
}
