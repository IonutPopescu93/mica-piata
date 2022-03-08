package com.sda.project.mapper;

import com.sda.project.dto.UserDto;
import com.sda.project.model.RoleType;
import com.sda.project.model.User;
import com.sda.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.List;

@Component
public class UserMapper {


    @Autowired
    private RoleRepository roleRepository;

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
        dto.setRole(entity.getRoles());
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
