package com.sda.project.mapper;

import com.sda.project.dto.UserDto;
import com.sda.project.model.Role;
import com.sda.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User map(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setDateOfBirth(LocalDate.parse(userDto.getDateOfBirth()));
        user.setRole(Role.valueOf(userDto.getRole()));

        String passwordInPlainText = userDto.getPassword();
        String passwordInCryptoText = bCryptPasswordEncoder.encode(passwordInPlainText);

        user.setPassword(passwordInCryptoText);

        return user;
    }

    public UserDto map(User user) {

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setDateOfBirth(String.valueOf(user.getDateOfBirth()));

        userDto.setPassword(user.getPassword());


        return userDto;
    }

    public List<UserDto> map(List<User> users) {
        List<UserDto> result = new ArrayList<>();

        for (User user : users) {
            result.add(map(user));
        }
        return result;
    }
}
