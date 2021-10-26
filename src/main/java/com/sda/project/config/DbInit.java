package com.sda.project.config;

import com.sda.project.model.Role;
import com.sda.project.model.User;
import com.sda.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbInit {

    // field dependency injection
    @Autowired
    private UserRepository userRepository;

    // runs after spring boot application is ready
    @Bean
    public CommandLineRunner initData() {
        return args -> {
            createMainAdmin();
        };
    }

    private User createMainAdmin() {
        User admin = new User(
                "admin@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "jon",
                "snow");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
        return admin;
    }
}
