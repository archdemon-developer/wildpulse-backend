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
        Optional<WPUser> possibleDuplicateUser =
                wpUserRepository.findByEmail(wpUserRequest.getEmail());
        if (possibleDuplicateUser.isEmpty()) {
            WPUser wpUserToCreate =
                    WPUser.builder()
                            .email(wpUserRequest.getEmail())
                            .userName(wpUserRequest.getUserName())
                            .build();
            WPUser createdWPUser = wpUserRepository.save(wpUserToCreate);
            return new WPUserResponse(
                    createdWPUser.getId(), createdWPUser.getUserName(), createdWPUser.getEmail());
        } else {
            log.error(
                    "Exiting create user as app is trying to create a user with already existing email Id.");
            throw new DataIntegrityViolationException(
                    "User with email: " + wpUserRequest.getEmail() + " already exists!");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public WPUserResponse getUserById(long userId) {
        Optional<WPUser> wpUserOptional = wpUserRepository.findById(userId);
        WPUser existingWPUser =
                wpUserOptional.orElseThrow(
                        () -> {
                            log.error(
                                    "Exiting get user by id as user with given user id is not present");
                            return new EntityNotFoundException(
                                    "User with id: " + userId + " not found");
                        });
        return new WPUserResponse(
                existingWPUser.getId(), existingWPUser.getUserName(), existingWPUser.getEmail());
    }
}
