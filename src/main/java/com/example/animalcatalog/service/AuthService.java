package com.example.animalcatalog.service;

import com.example.animalcatalog.domain.dto.security.LoginRequest;
import com.example.animalcatalog.domain.dto.security.LoginResponseDto;
import com.example.animalcatalog.domain.dto.security.SignUpRequest;
import com.example.animalcatalog.domain.dto.security.SignupResponseDto;

/**
 * @author ilyin
 * @since 10.07.2022
 */
public interface AuthService {
    LoginResponseDto login(LoginRequest loginRequest);

    SignupResponseDto signUp(SignUpRequest signUpRequest);
}
