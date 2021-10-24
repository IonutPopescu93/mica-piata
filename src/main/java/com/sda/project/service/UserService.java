package com.sda.project.service;

import com.sda.project.dto.ChangePasswordDto;
import com.sda.project.dto.UserDto;
import com.sda.project.mapper.UserMapper;
import com.sda.project.model.User;
import com.sda.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(UserDto userDto) {

        User user = userMapper.map(userDto);

        userRepository.save(user);

    }

    public void changePassword(ChangePasswordDto changePasswordDto, String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (!optionalUser.isPresent()) {
            return;
        }
        User user = optionalUser.get();
        String passwordInCryptoText = bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword());
        user.setPassword(passwordInCryptoText);
        userRepository.save(user);
    }
}
