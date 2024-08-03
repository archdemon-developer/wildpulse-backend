package com.wildpulse.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.wildpulse.backend.models.requests.WPUserRequest;
import com.wildpulse.backend.models.responses.WPUserResponse;
import com.wildpulse.backend.services.WPUserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class WPUserControllerTests {

    @MockBean private WPUserService wpUserService;

    @Autowired private WPUserController wpUserController;

    @Test
    public void testCreateUser() {
        WPUserRequest userToCreate =
                new WPUserRequest("uname", "fname", "lname", "fname.lname@email.com");
        WPUserResponse createdUserResponse =
                new WPUserResponse(
                        1,
                        userToCreate.firstName() + " " + userToCreate.lastName(),
                        userToCreate.userName(),
                        userToCreate.email());
        when(wpUserService.createUser(ArgumentMatchers.any(WPUserRequest.class)))
                .thenReturn(createdUserResponse);
        WPUserResponse receivedResponse = wpUserController.createUser(userToCreate);
        assertEquals(createdUserResponse.id(), receivedResponse.id());
        assertEquals(createdUserResponse.userName(), receivedResponse.userName());
        assertEquals(createdUserResponse.email(), receivedResponse.email());
        assertEquals(createdUserResponse.name(), receivedResponse.name());
    }

    @Test
    public void testGetUser() {
        long userId = 1L;
        WPUserResponse fetchUserResponse =
                new WPUserResponse(1, "fname", "lname", "fname.lname@email.com");
        when(wpUserService.getUserById(ArgumentMatchers.any(Long.class)))
                .thenReturn(fetchUserResponse);
        WPUserResponse receivedResponse = wpUserController.getUserById(userId);
        assertEquals(fetchUserResponse.id(), receivedResponse.id());
        assertEquals(fetchUserResponse.userName(), receivedResponse.userName());
        assertEquals(fetchUserResponse.email(), receivedResponse.email());
        assertEquals(fetchUserResponse.name(), receivedResponse.name());
    }
}
