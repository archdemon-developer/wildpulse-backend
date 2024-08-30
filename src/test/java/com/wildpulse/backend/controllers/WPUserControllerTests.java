package com.wildpulse.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.wildpulse.backend.models.requests.WPUserRequest;
import com.wildpulse.backend.models.responses.WPUserResponse;
import com.wildpulse.backend.services.WPUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WPUserControllerTests {

    @Mock private WPUserService wpUserService;

    @InjectMocks private WPUserController wpUserController;

    private long id;

    @BeforeEach
    public void setUp() {
        id = 1L;
    }

    @Test
    public void testCreateUser() {
        WPUserRequest userToCreate = new WPUserRequest("uname", "fname.lname@email.com");
        WPUserResponse createdUserResponse =
                new WPUserResponse(id, userToCreate.getUserName(), userToCreate.getEmail());
        when(wpUserService.createUser(ArgumentMatchers.any(WPUserRequest.class)))
                .thenReturn(createdUserResponse);
        WPUserResponse receivedResponse = wpUserController.createUser(userToCreate);
        assertEquals(createdUserResponse.getId(), receivedResponse.getId());
        assertEquals(createdUserResponse.getUserName(), receivedResponse.getUserName());
        assertEquals(createdUserResponse.getEmail(), receivedResponse.getEmail());
    }

    @Test
    public void testGetUser() {
        WPUserResponse fetchUserResponse = new WPUserResponse(id, "uname", "fname.lname@email.com");
        when(wpUserService.getUserById(ArgumentMatchers.anyLong())).thenReturn(fetchUserResponse);
        WPUserResponse receivedResponse = wpUserController.getUserById(id);
        assertEquals(fetchUserResponse.getId(), receivedResponse.getId());
        assertEquals(fetchUserResponse.getUserName(), receivedResponse.getUserName());
        assertEquals(fetchUserResponse.getEmail(), receivedResponse.getEmail());
    }
}
