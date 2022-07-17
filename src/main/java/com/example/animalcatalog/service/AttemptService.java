package com.example.animalcatalog.service;

import com.example.animalcatalog.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ilyin
 * @since 11.07.2022
 */
public interface AttemptService {

    int FAIL_ATTEMPT_MAX = 10;

    void failAttemptIncrease(User user);

    void failAttemptReset(String userName);

    void lock(User user);

    boolean timeIntervalUnlock(User user);
}
