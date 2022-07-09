package com.example.animalcatalog.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author ilyin
 * @since 08.07.2022
 */
@ResponseStatus(value = NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(UUID id, String message) {
        super(message + "not found: id=" + id);
    }

    public EntityNotFoundException(Integer id, String message) {
        super(message + "not found: id=" + id);
    }
}

