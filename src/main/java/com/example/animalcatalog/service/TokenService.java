package com.example.animalcatalog.service;


import com.example.animalcatalog.domain.entity.User;

/**
 * Service Interface to Generate and Validate Token
 *
 * @author ilyin
 * @since 10.07.2022
 */
public interface TokenService {
    String generateToken(User user);

    String extractUsernameAndValidate(String token);
}
