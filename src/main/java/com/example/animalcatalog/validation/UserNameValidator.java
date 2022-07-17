package com.example.animalcatalog.validation;

/*
 * @author ilyin
 * @since 14.07.2022
 */
import com.example.animalcatalog.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserNameValidator implements ConstraintValidator<UserNameUnique, String> {

    final UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.existsByUserName(value);
    }
}
