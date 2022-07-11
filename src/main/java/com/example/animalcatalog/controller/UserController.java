package com.example.animalcatalog.controller;

import com.example.animalcatalog.domain.dto.UserCreateDto;
import com.example.animalcatalog.domain.dto.UserDto;
import com.example.animalcatalog.domain.dto.UserUpdateDto;
import com.example.animalcatalog.domain.exception.EntityNotFoundException;
import com.example.animalcatalog.domain.mapper.UserMapper;
import com.example.animalcatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller to work with User Entity
 * @author ilyin
 * @since 07.07.2022
 * path = http://localhost:8081/api/v1.0/users
 */

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable(name = "userId") UUID id) {
        return Optional.of(id)
                .map(userService::get)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(id, "User"));
    }

    /**
     * Update user by id
     * @return UserDto on JSON format
     */
    @PatchMapping("/{userId}")
    public UserDto update(@PathVariable(name = "userId") UUID id, @RequestBody UserUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(userMapper::fromUpdateDto)
                .map(toUpdate -> userService.update(id, toUpdate))
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(id, "User"));
    }

    /**
     * Delete user by id
     */
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(name = "userId") UUID id) {
        userService.delete(id);
    }

    /**
     * Testing string
     * @return String
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

}
