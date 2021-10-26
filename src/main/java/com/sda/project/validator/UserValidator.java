package com.sda.project.validator;

import com.sda.project.dto.CreateUser;
import com.sda.project.model.Role;
import com.sda.project.model.User;
import com.sda.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(CreateUser dto, BindingResult bindingResult) {

        if (dto.getFirstName().length() < 2) {
            FieldError fieldError = new FieldError("userDto","firstName","Numele tău trebuie să conțină cel puțin două caractere!");
            bindingResult.addError(fieldError);
        }

        if (dto.getLastName().length() < 2) {
            FieldError fieldError = new FieldError("userDto","lastName","Prenumele tău trebuie să conțină cel puțin două caractere!");
            bindingResult.addError(fieldError);
        }

        if (!dto.getEmail().contains("@")) {
            FieldError fieldError = new FieldError("userDto", "email", "Email invalid!");
            bindingResult.addError(fieldError);
        }

        LocalDate dateOfBirth = null;
        try {
            dateOfBirth = LocalDate.parse(dto.getDateOfBirth());
        } catch (Exception e) {
            FieldError fieldError = new FieldError("userDto", "dateOfBirth", "Dată de naștere invalidă");
            bindingResult.addError(fieldError);
        }
        if (dateOfBirth != null && LocalDate.now().isBefore(dateOfBirth)) {
            FieldError fieldError = new FieldError("userDto", "dateOfBirth", "Data de naștere nu poate fi în viitor!");
            bindingResult.addError(fieldError);
        }

        if (dto.getPassword().length() < 5) {
            FieldError fieldError = new FieldError("userDto", "password", "Introdu cel puțin 5 caractere");
            bindingResult.addError(fieldError);
        }

        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());

        if (optionalUser.isPresent()) {
            FieldError fieldError = new FieldError("userDto", "email", "Adresa de email este deja folosită");
            bindingResult.addError(fieldError);
        }

        if (dto.getPhoneNumber().length() < 10) {
            FieldError fieldError = new FieldError("userDto","phoneNumber","Număr de telefon prea scurt!");
            bindingResult.addError(fieldError);
        }
        if (dto.getPhoneNumber().length() > 10) {
            FieldError fieldError = new FieldError("userDto","phoneNumber","Număr de telefon prea lung!");
            bindingResult.addError(fieldError);
        }

        if (!dto.getAgreeWithConditions()) {
            FieldError fieldError = new FieldError("userDto", "agreeWithConditions", "Trebuie să fii de acord cu termeni și condițiile site-ului web pentru a te putea înregistra!");
            bindingResult.addError(fieldError);
        }

        try{
//            Role.valueOf(dto.getRole());
        } catch (Exception e){
            FieldError fieldError = new FieldError("userDto","role","Selectați rolul!");
            bindingResult.addError(fieldError);
        }
    }
}
