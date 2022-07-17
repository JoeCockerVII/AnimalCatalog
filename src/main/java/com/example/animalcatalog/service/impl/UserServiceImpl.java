package com.example.animalcatalog.service.impl;

import com.example.animalcatalog.domain.entity.User;
import com.example.animalcatalog.domain.mapper.UserMapper;
import com.example.animalcatalog.repository.UserRepository;
import com.example.animalcatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author ilyin
 * @since 07.07.2022
 */

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User get(UUID id) {
        User user = userRepository.getById(id);
        Hibernate.initialize(user);
        return user;
    }

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(UUID id, User user) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> userMapper.merge(current, user))
                .map(userRepository::save)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
}
