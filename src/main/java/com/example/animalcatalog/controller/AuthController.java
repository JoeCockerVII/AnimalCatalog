package com.example.animalcatalog.controller;

import com.example.animalcatalog.domain.dto.security.LoginRequest;
import com.example.animalcatalog.domain.dto.security.LoginResponseDto;
import com.example.animalcatalog.domain.dto.security.SignUpRequest;
import com.example.animalcatalog.domain.dto.security.SignupResponseDto;
import com.example.animalcatalog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of users authorization
 * @author ilyin
 * @since 10.07.2022
 */

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Login of the User
     * @return LoginResponseDto on JSON format
     */
    @PostMapping("login")
    public LoginResponseDto login(@RequestBody LoginRequest loginRequest) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();

        return authService.login(loginRequest);
    }

    /**
     * Signup of the User
     * @return String
     */
    @PostMapping("sign-up")
    public SignupResponseDto signUp(@RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }
}