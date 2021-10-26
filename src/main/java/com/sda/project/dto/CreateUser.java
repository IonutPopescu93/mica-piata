package com.sda.project.dto;

import com.sda.project.model.Role;
import lombok.Data;

@Data
public class CreateUser {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private String password;
    private Role role;
    private Boolean agreeWithConditions;
}
