package com.example.animalcatalog.validation;

/*
 * @author ilyin
 * @since 14.07.2022
 */
import com.example.animalcatalog.service.AnimalService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class AnimalNameValidator implements ConstraintValidator<AnimalNameUnique, String> {

    final AnimalService animalService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !animalService.existsByAnimalName(value);
    }
}
