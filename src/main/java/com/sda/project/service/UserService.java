package com.sda.project.service;

import com.sda.project.dto.ChangePasswordDto;
import com.sda.project.dto.CreateUser;
import com.sda.project.mapper.UserMapper;
import com.sda.project.model.Role;
import com.sda.project.model.User;
import com.sda.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // constructor dependency injection
    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // create

    public void save(CreateUser dto) {
        User user = userMapper.map(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // read

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // find user in db
        return userRepository.findByEmail(email)
                // add permission
                .map(user -> {
                    // get roles
                    Role role = user.getRole();

                    // convert user to user details
                    // return user with permissions
                    return new UserPrincipal(user, role);
                })
                // throw exception
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }

    // TODO: implement in another user story

    // update
    public void changePassword(ChangePasswordDto changePasswordDto, String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (!optionalUser.isPresent()) {
            return;
        }
        User user = optionalUser.get();
        String passwordInCryptoText = passwordEncoder.encode(changePasswordDto.getNewPassword());
        user.setPassword(passwordInCryptoText);
        userRepository.save(user);
    }

    // delete

}
