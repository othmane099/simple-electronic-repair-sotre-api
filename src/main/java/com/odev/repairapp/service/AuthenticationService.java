package com.odev.repairapp.service;

import com.odev.repairapp.exception.RepairAppException;
import com.odev.repairapp.model.Token;
import com.odev.repairapp.model.TokenType;
import com.odev.repairapp.model.User;
import com.odev.repairapp.repository.TokenRepository;
import com.odev.repairapp.repository.UserRepository;
import com.odev.repairapp.request.AuthenticationRequest;
import com.odev.repairapp.request.RegisterRequest;
import com.odev.repairapp.response.AuthenticationResponse;
import com.odev.repairapp.response.UserResponse;
import com.odev.repairapp.utils.ErrorCode;
import com.odev.repairapp.validator.AuthenticationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponse register(RegisterRequest request) {
        List<String> errors = AuthenticationValidator.validate(request);
        if (!errors.isEmpty())
            throw new RepairAppException("User's data not valid", ErrorCode.USER_NOT_VALID, errors);

        User user = RegisterRequest.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = repository.save(user);
        return UserResponse.toResponse(savedUser);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}