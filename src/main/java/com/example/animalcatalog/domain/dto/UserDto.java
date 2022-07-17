package com.example.animalcatalog.domain.dto;

import com.example.animalcatalog.domain.entity.Role;
import com.example.animalcatalog.validation.UserNameUnique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author ilyin
 * @since 07.07.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class UserDto {
    UUID id;
    String userName;
    Role role;
}
