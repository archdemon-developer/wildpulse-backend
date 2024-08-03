package com.wildpulse.backend.controllers;

import com.wildpulse.backend.constants.WPAPIConstants;
import com.wildpulse.backend.constants.WPDefaultConstants;
import com.wildpulse.backend.models.requests.WPUserRequest;
import com.wildpulse.backend.models.responses.WPUserResponse;
import com.wildpulse.backend.services.WPUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = WPAPIConstants.WP_BASE_USERS_PATH)
@Slf4j
public class WPUserController {

    private final WPUserService wpUserService;

    @Autowired
    public WPUserController(WPUserService wpUserService) {
        this.wpUserService = wpUserService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WPUserResponse createUser(@Valid @RequestBody WPUserRequest wpUserRequest) {
        log.info(
                "Entering create user controller method with request body: {} ",
                wpUserRequest.toString());
        return wpUserService.createUser(wpUserRequest);
    }

    @GetMapping(
            path = WPAPIConstants.WP_FETCH_USER_BY_ID_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public WPUserResponse getUserById(
            @PathVariable(name = WPDefaultConstants.WP_VARIABLE_USER_ID) long userId) {
        log.info("Entering get user by id controller method with user id: {}", userId);
        return wpUserService.getUserById(userId);
    }
}
