package com.example.animalcatalog.repository;

import com.example.animalcatalog.domain.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author ilyin
 * @since 08.07.2022
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    /**
     * Get all user animals
     * @return animals Page
     */
    Page<Animal> findAnimalByUserId(UUID userId, Pageable pageable);

    /**
     * Animal existion check by name
     * @return animal existion mark
     */
    boolean existsByName(String name);

}

