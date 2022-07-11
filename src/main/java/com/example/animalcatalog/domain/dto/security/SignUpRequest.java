package com.example.animalcatalog.domain.dto.security;

import com.example.animalcatalog.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author ilyin
 * @since 10.07.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class SignUpRequest {
    String userName;
    String password;
    Role role;
}
