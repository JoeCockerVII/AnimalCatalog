package com.example.animalcatalog.service.impl;

import com.example.animalcatalog.domain.entity.Animal;
import com.example.animalcatalog.domain.entity.User;
import com.example.animalcatalog.domain.mapper.AnimalMapper;
import com.example.animalcatalog.repository.AnimalRepository;
import com.example.animalcatalog.service.AnimalService;
import com.example.animalcatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author ilyin
 * @since 08.07.2022
 */
@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    final List<String> animalTypes = Arrays.asList("Dog","Cat","Cow","Pig");

    private final AnimalMapper animalMapper;
    private final AnimalRepository animalRepository;
    private final UserService userService;

    @Override
    public Animal get(UUID id) {
        Animal animal = animalRepository.getById(id);
        Hibernate.initialize(animal);
        return animal;
    }

    @Override
    @Transactional
    public Animal create(UUID userId, Animal animal) {
        final User user = userService.get(userId);
        user.addAnimal(animal);
        return animalRepository.save(animal);
    }

    @Override
    @Transactional
    public Animal update(UUID id, Animal animal) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> animalMapper.merge(current, animal))
                .map(animalRepository::save)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        animalRepository.deleteById(id);
    }

    @Transactional
    public Page<Animal> getUserAnimals(UUID userId, Pageable pageable) {
        return animalRepository.findAnimalByUserId(userId, pageable);
    }
}
