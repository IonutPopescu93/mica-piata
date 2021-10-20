package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {


    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String dateOfBirth;

    private String password;

    private String role;

    private Boolean agreeWithConditions;

}
