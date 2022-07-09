package com.example.animalcatalog.controller;

import com.example.animalcatalog.domain.dto.AnimalCreateDto;
import com.example.animalcatalog.domain.dto.AnimalDto;
import com.example.animalcatalog.domain.dto.AnimalUpdateDto;
import com.example.animalcatalog.domain.exception.EntityNotFoundException;
import com.example.animalcatalog.domain.mapper.AnimalMapper;
import com.example.animalcatalog.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller to work with Animal Entity
 * @author ilyin
 * @since 07.07.2022
 * path = http://localhost:8080/api/v1.0/users/{userId}/animals
 */
@RestController
@RequestMapping(path = "users/{userId}/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalMapper animalMapper;
    private final AnimalService animalService;

    @GetMapping("/{animalId}")
    public AnimalDto get(@PathVariable(name = "animalId") UUID id) {
        return Optional.of(id)
                .map(animalService::get)
                .map(animalMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(id, "Animal"));
    }

    /**
     * Add new animal
     * @return AnimalDto on JSON format
     */
    @PostMapping
    public AnimalDto create(@PathVariable(name = "userId") UUID userId,@RequestBody AnimalCreateDto createDto) {
        return Optional.ofNullable(createDto)
                .map(animalMapper::fromCreateDto)
                .map(current -> animalService.create(userId,current))
                .map(animalMapper::toDto)
                .orElseThrow();
    }

    /**
     * Update animal by id
     * @return AnimalDto on JSON format
     */
    @PatchMapping("/{animalId}")
    public AnimalDto update(@PathVariable(name = "animalId") UUID id, @RequestBody AnimalUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(animalMapper::fromUpdateDto)
                .map(toUpdate -> animalService.update(id, toUpdate))
                .map(animalMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(id, "Animal"));
    }

    /**
     * Delete animal by id
     */
    @DeleteMapping("/{animalId}")
    public void delete(@PathVariable(name = "animalId") UUID id) {
        animalService.delete(id);
    }

    /**
     * Get all animals by userId
     * @return Page of animals on JSON format
     */
    @GetMapping()
    public Page<AnimalDto> getUserAnimals(@PathVariable UUID userId, Pageable pageable) {
        return Optional.of(userId)
                .map(it -> animalService.getUserAnimals(userId, pageable))
                .map(it -> it.map(animalMapper::toDto))
                .orElseThrow();
    }

}
