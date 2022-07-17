package com.example.animalcatalog.domain.dto;

import com.example.animalcatalog.validation.AnimalNameUnique;
import com.example.animalcatalog.validation.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author ilyin
 * @since 07.07.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class AnimalCreateDto {
    @AnimalType
    String animalType;
    @AnimalNameUnique
    String name;
    String gender;
    String dayOfBirth;
}