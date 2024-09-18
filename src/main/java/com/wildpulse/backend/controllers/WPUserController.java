package com.wildpulse.backend.controllers;

import com.wildpulse.backend.constants.WPAPIConstants;
import com.wildpulse.backend.constants.WPDefaultConstants;
import com.wildpulse.backend.models.requests.WPUserRequest;
import com.wildpulse.backend.models.responses.WPUserResponse;
import com.wildpulse.backend.services.WPUserService;
import com.wildpulse.commons.configurations.auth.Authenticated;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = WPAPIConstants.WP_BASE_USERS_PATH)
@RequiredArgsConstructor
public class WPUserController {

    private final WPUserService wpUserService;

    @Authenticated
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WPUserResponse createUser(@Valid @RequestBody WPUserRequest wpUserRequest) {
        log.info(
                "Entering create user controller method with request body: {} ",
                wpUserRequest.toString());
        WPUserResponse createdUserResponse = wpUserService.createUser(wpUserRequest);
        log.info("User created successfully. response: {}", createdUserResponse.toString());
        return createdUserResponse;
    }

    @Authenticated
    @GetMapping(
            path = WPAPIConstants.WP_FETCH_USER_BY_ID_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WPUserResponse getUserById(
            @PathVariable(name = WPDefaultConstants.WP_VARIABLE_USER_ID) String userId) {
        log.info("Entering get user by id controller method with user id: {}", userId);
        WPUserResponse userResponse = wpUserService.getUserById(userId);
        log.info("User fetched successfully: {}", userResponse);
        return userResponse;
    }
}
