package com.example.animalcatalog.domain.mapper;

import com.example.animalcatalog.domain.dto.*;
import com.example.animalcatalog.domain.entity.Animal;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * Animal data fields Mapper
 * @author ilyin
 * @since 07.07.2022
 */
@Mapper
public interface AnimalMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Animal fromCreateDto(AnimalCreateDto source);

    @Mapping(target = "user", ignore = true)
    Animal fromUpdateDto(AnimalUpdateDto source);

    AnimalDto toDto(Animal source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Animal merge(@MappingTarget Animal target, Animal source);

}
