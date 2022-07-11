package com.example.animalcatalog.domain.mapper;

import com.example.animalcatalog.domain.dto.UserCreateDto;
import com.example.animalcatalog.domain.dto.UserDto;
import com.example.animalcatalog.domain.dto.UserUpdateDto;
import com.example.animalcatalog.domain.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * User data fields Mapper
 * @author ilyin
 * @since 07.07.2022
 */

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "animals", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    User fromCreateDto(UserCreateDto source);

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "animals", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    User fromUpdateDto(UserUpdateDto source);

    UserDto toDto(User source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    User merge(@MappingTarget User target, User source);

}
