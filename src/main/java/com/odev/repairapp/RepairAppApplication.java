package com.odev.repairapp;

import com.odev.repairapp.model.QuickReply;
import com.odev.repairapp.model.Role;
import com.odev.repairapp.model.User;
import com.odev.repairapp.repository.QuickReplyRepository;
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
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        PasswordEncoder passwordEncoder,
                                        QuickReplyRepository quickReplyRepository){
        return args -> {
            userRepository.save(new User(1L, "admin", "admin", "admin@mail.com", passwordEncoder.encode("123456"), Role.ADMIN, null));

            quickReplyRepository.save(new QuickReply(null, "name3", "body3", null, null));
            quickReplyRepository.save(new QuickReply(null, "name2", "body2", null, null));
            quickReplyRepository.save(new QuickReply(null, "name1", "body1", null, null));

        };
    }

}
