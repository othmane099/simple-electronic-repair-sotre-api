package com.odev.repairapp;

import com.odev.repairapp.model.Role;
import com.odev.repairapp.model.User;
import com.odev.repairapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class RepairAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(RepairAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            userRepository.save(new User(1L, "admin", "admin", "admin@mail.com", passwordEncoder.encode("123456"), Role.ADMIN, null));
        };
    }

}
