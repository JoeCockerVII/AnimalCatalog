package com.example.animalcatalog.service;

import com.example.animalcatalog.domain.entity.User;

import java.util.UUID;

/**
 * Service Interface to work with UserRepository
 * @author ilyin
 * @since 07.07.2022
 */

public interface UserService {

    /**
     * Get user by user id
     * @return User Entity
     */
    User get(UUID id);

    /**
     * Add new user
     * @return User Entity
     */
    User create(User user);

    /**
     * Update user by id
     * @return User Entity
     */
    User update(UUID id, User user);

    /**
     * Delete user by id
     */
    void delete(UUID id);

}
