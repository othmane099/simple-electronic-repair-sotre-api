package com.odev.repairapp;

import com.odev.repairapp.model.*;
import com.odev.repairapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static com.odev.repairapp.model.Authority.*;

@SpringBootApplication
@RequiredArgsConstructor
public class RepairAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(RepairAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        PasswordEncoder passwordEncoder,
                                        RepairPriorityRepository repairPriorityRepository,
                                        QuickReplyRepository quickReplyRepository,
                                        RepairStatusRepository repairStatusRepository,
                                        BrandRepository brandRepository,
                                        DeviceRepository deviceRepository,
                                        DefectRepository defectRepository){
        return args -> {
            userRepository.save(new User(1L, "admin", "admin", "admin@mail.com", passwordEncoder.encode("123456"), Arrays.stream(Authority.values()).toList(), null));
            userRepository.save(new User(2L, "user", "user", "user@mail.com", passwordEncoder.encode("123456"), List.of(MANAGE_DEVICE, MANAGE_BRAND, MANAGE_DEFECT), null));

            quickReplyRepository.save(new QuickReply(null, "name3", "body3", null, null));
            quickReplyRepository.save(new QuickReply(null, "name2", "body2", null, null));
            quickReplyRepository.save(new QuickReply(null, "name1", "body1", null, null));

            repairStatusRepository.save(new RepairStatus(null, "pending", null, null));
            repairStatusRepository.save(new RepairStatus(null, "opening", null, null));
            repairStatusRepository.save(new RepairStatus(null, "closed", null, null));

            brandRepository.save(new Brand(null, "hp", null, null, null));
            Brand brand = brandRepository.save(new Brand(null, "DELL", null, null, null));
            brandRepository.save(new Brand(null, "Acer", null, null, null));

            Device device = deviceRepository.save(new Device(null, "inspiron 15", "3000 series", null, null, null, brand, null));

            Defect defect = defectRepository.save(new Defect(null, "broken screen", "3", 1.0, 50.0, null, null, device, null));

            repairPriorityRepository.save(new RepairPriority(null,0, "normal", 0.0, null, null));
        };
    }

}
