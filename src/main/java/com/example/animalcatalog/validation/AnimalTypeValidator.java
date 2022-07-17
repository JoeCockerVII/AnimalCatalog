package com.example.animalcatalog.validation;

/*
 * @author ilyin
 * @since 14.07.2022
 */
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AnimalTypeValidator implements ConstraintValidator<AnimalType, String> {

    final List<String> animalTypes = Arrays.asList("Dog","Cat","Cow","Pig");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return animalTypes.contains(value);
    }
}
