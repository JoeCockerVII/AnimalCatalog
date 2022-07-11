package com.example.animalcatalog.domain.dto.security;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

/**
 * @author ilyin
 * @since 10.07.2022
 */
@Value
@Setter
@Builder
@Jacksonized
@RequiredArgsConstructor

public class SignupResponseDto {
    String token;
    UUID userId;
}