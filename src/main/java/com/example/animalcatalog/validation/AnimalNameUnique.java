package com.example.animalcatalog.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ilyin
 * @since 15.07.2022
 */
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = AnimalNameValidator.class)
@Documented
public @interface AnimalNameUnique {
    String message() default "{AnimalName.notValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}