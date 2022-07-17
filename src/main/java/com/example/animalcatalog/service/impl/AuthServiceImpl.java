package com.example.animalcatalog.service.impl;

import com.example.animalcatalog.domain.entity.User;
import com.example.animalcatalog.repository.UserRepository;
import com.example.animalcatalog.domain.dto.security.LoginRequest;
import com.example.animalcatalog.domain.dto.security.LoginResponseDto;
import com.example.animalcatalog.domain.dto.security.SignUpRequest;
import com.example.animalcatalog.domain.dto.security.SignupResponseDto;
import com.example.animalcatalog.service.AttemptService;
import com.example.animalcatalog.service.AuthService;
import com.example.animalcatalog.service.TokenService;
import com.example.animalcatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service to LogIn and SignUp of Users
 * @author ilyin
 * @since 10.07.2022
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final AttemptService attemptService;

    @Override
    public LoginResponseDto login(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            attemptService.failAttemptReset(user.getUserName());

            return new LoginResponseDto(tokenService.generateToken(user), user.getId());
        } else {
            // Add logic to LOCK
            if (user.isAccountNonLocked()) {
                if (user.getAttemptCount() < attemptService.FAIL_ATTEMPT_MAX - 1) {
                    attemptService.failAttemptIncrease(user);
                } else {
                    attemptService.lock(user);
                    System.out.println("Your account has been locked due to 3 failed attempts."
                            + " It will be unlocked after 1 hour.");
                }
            } else {
                if (attemptService.timeIntervalUnlock(user)) {
                    System.out.println("Your account has been unlocked. Please try to login again.");
                }
            }

            throw new RuntimeException("Wrong Password");
        }
    }

    @Override
    public SignupResponseDto signUp(SignUpRequest signUpRequest) {

        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setRole(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userService.create(user);
        return new SignupResponseDto(tokenService.generateToken(user),user.getId());
    }
}
