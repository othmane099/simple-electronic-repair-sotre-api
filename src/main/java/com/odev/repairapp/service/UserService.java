package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.User;
import com.odev.repairapp.repository.UserRepository;
import com.odev.repairapp.request.RegisterRequest;
import com.odev.repairapp.response.UserResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.validator.AuthenticationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public UserResponse save(RegisterRequest request) {
        List<String> errors = AuthenticationValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("User's data not valid", ErrorCode.USER_NOT_VALID, errors);

        User user = RegisterRequest.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = repository.save(user);
        return UserResponse.toResponse(savedUser);
    }
}
