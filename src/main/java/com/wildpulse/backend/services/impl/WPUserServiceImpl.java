package com.wildpulse.backend.services.impl;

import com.wildpulse.backend.models.WPUser;
import com.wildpulse.backend.models.requests.WPUserRequest;
import com.wildpulse.backend.models.responses.WPUserResponse;
import com.wildpulse.backend.repositories.WPUserRepository;
import com.wildpulse.backend.services.WPUserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class WPUserServiceImpl implements WPUserService {

    private final WPUserRepository wpUserRepository;

    @Autowired
    public WPUserServiceImpl(WPUserRepository wpUserRepository) {
        this.wpUserRepository = wpUserRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public WPUserResponse createUser(WPUserRequest wpUserRequest) {
        log.info("inside create user method of service class with request: {}", wpUserRequest);
        Optional<WPUser> possibleDuplicateUser =
                wpUserRepository.findByEmail(wpUserRequest.email());
        if (possibleDuplicateUser.isEmpty()) {
            WPUser wpUserToCreate =
                    WPUser.builder()
                            .email(wpUserRequest.email())
                            .userName(wpUserRequest.userName())
                            .name(
                                    String.format(
                                            "%s %s",
                                            wpUserRequest.firstName(), wpUserRequest.lastName()))
                            .build();
            WPUser createdWPUser = wpUserRepository.save(wpUserToCreate);
            log.info("Exiting create user method of service class");
            return new WPUserResponse(
                    createdWPUser.getId(),
                    createdWPUser.getName(),
                    createdWPUser.getUserName(),
                    createdWPUser.getEmail());
        } else {
            log.error(
                    "Exiting create user as app is trying to create a user with already existing email Id.");
            throw new DataIntegrityViolationException(
                    "User with email: " + wpUserRequest.email() + " already exists!");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public WPUserResponse getUserById(long userId) {
        log.info("inside get user by method of service class with user id: {}", userId);
        Optional<WPUser> wpUserOptional = wpUserRepository.findById(userId);
        WPUser existingWPUser =
                wpUserOptional.orElseThrow(
                        () -> {
                            log.error(
                                    "Exiting create user as user with given user id isn ot present");
                            return new EntityNotFoundException(
                                    "User with id: " + userId + " not found");
                        });
        log.info("Exiting get user by id method of service class");
        return new WPUserResponse(
                existingWPUser.getId(),
                existingWPUser.getName(),
                existingWPUser.getUserName(),
                existingWPUser.getEmail());
    }
}
