package com.sda.project.mapper;

import com.sda.project.dto.UserDto;
import com.sda.project.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {

    public User map(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    public UserDto map(User entity){
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }

    public User update(User userToUpdate, UserDto data) {
        userToUpdate.setFirstName(data.getFirstName());
        userToUpdate.setLastName(data.getLastName());
        userToUpdate.setEmail(data.getEmail());
        userToUpdate.setPhoneNumber(data.getPhoneNumber());
        userToUpdate.setPassword(data.getPassword());
        return userToUpdate;
    }
}
