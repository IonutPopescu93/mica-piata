package com.sda.project.mapper;

import com.sda.project.dto.CreateUser;
import com.sda.project.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public User map(CreateUser dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        entity.setRole(dto.getRole());

        return entity;
    }

    public CreateUser map(User user) {
        CreateUser createUser = new CreateUser();
        createUser.setEmail(user.getEmail());
        createUser.setDateOfBirth(String.valueOf(user.getDateOfBirth()));
        createUser.setPassword(user.getPassword());
        return createUser;
    }

    public List<CreateUser> map(List<User> users) {
        List<CreateUser> result = new ArrayList<>();

        for (User user : users) {
            result.add(map(user));
        }
        return result;
    }
}
