package com.example.animalcatalog.service.impl;

import com.example.animalcatalog.domain.entity.User;
import com.example.animalcatalog.repository.UserRepository;
import com.example.animalcatalog.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ilyin
 * @since 11.07.2022
 */

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class AttemptServiceImpl implements AttemptService {

    private static final long LOCK_TIME_DURATION = 1 * 60 * 60 * 1000; // hours

    private final UserRepository userRepository;

    @Transactional
    public void failAttemptIncrease(User user) {
        User user1 = userRepository.findByUserName(user.getUserName()).get();
        user1.setAttemptCount(user.getAttemptCount() + 1);
        userRepository.save(user1);
    }

    @Transactional
    public void failAttemptReset(String userName) {
        User user1 = userRepository.findByUserName(userName).get();
        user1.setAttemptCount(0);
        user1.setAccountNonLocked(true);
        userRepository.save(user1);
    }

    @Transactional
    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        userRepository.save(user);
    }

    @Transactional
    public boolean timeIntervalUnlock(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setAttemptCount(0);

            userRepository.save(user);

            return true;
        }

        return false;
    }

}
