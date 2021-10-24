package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ChangePasswordDto {

    private String currentPassword;
    private String newPassword;
    private String repeatNewPassword;
}
