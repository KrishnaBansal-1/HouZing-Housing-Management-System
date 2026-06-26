package com.krishna.project.HouZing.utility;

import com.krishna.project.HouZing.entity.HUser;
import com.krishna.project.HouZing.entity.type.UserRole;
import com.krishna.project.HouZing.repository.HUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ApplicationInitializer {

    private final HUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${manager.username}")
    private String username;

    @Value("${manager.password}")
    private String password;

    @Bean
    CommandLineRunner initAdmin() {
        return args -> {

            if (userRepository.findByUsername(username).isEmpty()) {

                HUser admin = HUser.builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .roles(Set.of(UserRole.ADMIN))
                        .build();

                userRepository.save(admin);
                System.out.println("Admin created successfully.----------------------------------------------------------------");
            }
        };
    }
}