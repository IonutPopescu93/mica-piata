package com.sda.project.validator;

import com.sda.project.dto.ChangePasswordDto;
import com.sda.project.model.User;
import com.sda.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class ChangePasswordValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void validate(ChangePasswordDto changePasswordDto, BindingResult bindingResult, String email) {

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getRepeatNewPassword())) {
            FieldError fieldError = new FieldError(
                    "changePasswordDto",
                    "repeatNewPassword",
                    "Passwords must match");
            bindingResult.addError(fieldError);
        }
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            FieldError fieldError = new FieldError(
                    "changePasswordDto",
                    "currentPassword",
                    "Something happened with your user");
            bindingResult.addError(fieldError);
        }
        String currentPassword = optionalUser.get().getPassword();
        String dtoCurrentPassword = passwordEncoder.encode(changePasswordDto.getCurrentPassword());
        System.out.println(currentPassword + " " + dtoCurrentPassword);

        if (!passwordEncoder.matches(changePasswordDto.getCurrentPassword(), currentPassword)) {
            FieldError fieldError = new FieldError(
                    "changePasswordDto",
                    "currentPassword",
                    "Password is incorrect");
            bindingResult.addError(fieldError);
        }
    }
}
