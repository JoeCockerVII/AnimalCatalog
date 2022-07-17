package com.example.animalcatalog.repository;

import com.example.animalcatalog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author ilyin
 * @since 07.07.2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Get user by email
     * @return Optional<User>
     */
    Optional<User> findByUserName(String userName);

    /**
     * User existion check by userName
     * @return user existion mark
     */
    boolean existsByUserName(String userName);
}

