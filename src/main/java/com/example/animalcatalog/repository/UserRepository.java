package com.example.animalcatalog.repository;

import com.example.animalcatalog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author ilyin
 * @since 07.07.2022
 */

public interface UserRepository extends JpaRepository<User, UUID> {

}

