package com.example.animalcatalog.service;

import com.example.animalcatalog.domain.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Service Interface to work with AnimalRepository
 * @author ilyin
 * @since 08.07.2022
 */
public interface AnimalService {

    /**
     * Get user by animal id
     * @return Animal Entity
     */
    Animal get(UUID id);

    /**
     * Add new animal
     * @return Animal Entity
     */
    Animal create(UUID userId, Animal animal);

    /**
     * Update animal by id
     * @return Animal Entity
     */
    Animal update(UUID id, Animal animal);

    /**
     * Delete animal by id
     */
    void delete(UUID id);

    /**
     * Get all User Animals
     * @return Orders Set(Page)
     */
    Page<Animal> getUserAnimals(UUID userId, Pageable pageable);

    /**
     * Animal existion check by animalName
     * @return animal existion mark
     */
    boolean existsByAnimalName(String name);

}
