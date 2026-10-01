package com.hellowspring.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hellowspring.userservice.User.Role;
import com.hellowspring.userservice.User.UserEntity;
import com.hellowspring.userservice.User.UserRepository;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            if (!userRepository.existsByUsername("admin")) {

                UserEntity admin = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("password"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(admin);
            }
        };
    }
}

