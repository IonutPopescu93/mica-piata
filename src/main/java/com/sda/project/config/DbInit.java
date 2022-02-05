package com.sda.project.config;

import com.sda.project.controller.exception.ResourceAlreadyExistsException;
import com.sda.project.model.*;
import com.sda.project.repository.PrivilegeRepository;
import com.sda.project.repository.ProductRepository;
import com.sda.project.repository.RoleRepository;
import com.sda.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class DbInit {

    private static final Logger log = LoggerFactory.getLogger(DbInit.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public CommandLineRunner initialData() {
        return args -> {
            log.info("setup initial data");

//             create privileges
            Privilege readPrivilege = createPrivilegeIfNotFound(PrivilegeType.READ_PRIVILEGE);
            Privilege writePrivilege = createPrivilegeIfNotFound(PrivilegeType.WRITE_PRIVILEGE);

            // create roles
            createRoleIfNotFound(RoleType.ADMIN, Set.of(readPrivilege, writePrivilege));
            createRoleIfNotFound(RoleType.USER, Set.of(readPrivilege, writePrivilege));

            // create main admin, admin, user
            User mainAdmin = createMainAdmin();
            userRepository.save(mainAdmin);

            User admin = createAdmin();
            User user = createUser();

            //create products
            Product product = createFruct1();
            productRepository.save(product);
        };
    }

    //12345678
    private User createMainAdmin() {
        User admin = new User(
                "main@gmail.com",
                "{bcrypt}$2a$10$CY/Z8iyM6ZmHfk2WkiNUjuQyAXJEFxNT5rQGwwmPcxLdzMfjLZvPm",
                "jon",
                "snow");
        admin.setEnabled(true);
        Role adminRole = roleRepository.findByType(RoleType.ADMIN).orElseThrow();
        admin.addRole(adminRole);
        userRepository.save(admin);
        return admin;
    }

    private User createAdmin() {
        User admin = new User(
                "admin@gmail.com",
                "{bcrypt}$2a$10$CY/Z8iyM6ZmHfk2WkiNUjuQyAXJEFxNT5rQGwwmPcxLdzMfjLZvPm",
                "bill",
                "clinton");
        Role adminRole = roleRepository.findByType(RoleType.ADMIN).orElseThrow();
        admin.addRole(adminRole);
        userRepository.save(admin);
        return admin;
    }

    private User createUser() {
        User user = new User(
                "user@gmail.com",
                "{bcrypt}$2a$10$CY/Z8iyM6ZmHfk2WkiNUjuQyAXJEFxNT5rQGwwmPcxLdzMfjLZvPm",
                "alex",
                "vasile");
        user.setEnabled(true);
        Role userRole = roleRepository.findByType(RoleType.USER).orElseThrow();
        user.addRole(userRole);
        return userRepository.save(user);
    }

    @Transactional
    Role createRoleIfNotFound(RoleType type, Set<Privilege> privileges) {
        return (Role) roleRepository.findByType(type)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("role already exists");
                })
                .orElseGet(() -> {
                    Role role = new Role(type);
                    role.setPrivileges(privileges);
                    return roleRepository.save(role);
                });
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(PrivilegeType name) {
        return (Privilege) privilegeRepository.findByType(name)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("privilege already exists");
                })
                .orElseGet(() -> privilegeRepository.save(new Privilege(name)));
    }

    private Product createFruct1() {
        Product product = new Product();
        product.setProductName("Măr roșu");
        product.setPhoto("apple-500x500.jpg");
        product.setCategory(Category.FRUCTE);
        product.setShortDescription("assdasdasdasdasd");
        product.setPrice(8.5);
        product.setUnitsInStock(100.0);
        product.setAvailable(true);
        return product;

    }
}
