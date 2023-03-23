package com.odev.repairapp.repository;

import java.util.Optional;

import com.odev.repairapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}