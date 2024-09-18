package com.wildpulse.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.wildpulse.backend.models.WPUser;
import com.wildpulse.backend.models.requests.WPUserRequest;
import com.wildpulse.backend.models.responses.WPUserResponse;
import com.wildpulse.backend.repositories.WPUserRepository;
import com.wildpulse.backend.services.impl.WPUserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
public class WPUserServiceTests {

    @Mock private WPUserRepository wpUserRepository;

    @InjectMocks private WPUserServiceImpl wpUserService;

    private String id;

    @BeforeEach
    public void setUp() {
        id = "id";
    }

    @Test
    public void testCreateUserMethodSuccessfulCreation() {
        when(wpUserRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        WPUserRequest userToCreate = new WPUserRequest(id, "uname", "fname.lname@email.com", false, "photoUrl", false);
        WPUser createdUser = new WPUser(id, userToCreate.getUserName(), userToCreate.getEmail(), false, "photoUrl", false);
        WPUserResponse createdUserResponse =
                new WPUserResponse("id", "uname", "fname.lname@email.com", false, "photoUrl", true);;
        when(wpUserRepository.save(ArgumentMatchers.any(WPUser.class))).thenReturn(createdUser);
        WPUserResponse receivedResponse = wpUserService.createUser(userToCreate);
        assertEquals(createdUserResponse.getId(), receivedResponse.getId());
        assertEquals(createdUserResponse.getUserName(), receivedResponse.getUserName());
        assertEquals(createdUserResponse.getEmail(), receivedResponse.getEmail());
    }

    @Test
    public void testCreateUserAlreadyExists() {
        WPUserRequest userToCreate = new WPUserRequest(id, "uname", "fname.lname@email.com", false, "photoUrl", false);
        WPUser existingUser = new WPUser(id, userToCreate.getUserName(), userToCreate.getEmail(), false, "photoUrl", false);
        when(wpUserRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(existingUser));
        assertThrows(
                DataIntegrityViolationException.class,
                () -> wpUserService.createUser(userToCreate));
    }

    @Test
    public void testGetUserById() {
        WPUser foundUser = new WPUser(id, "uname", "fname.lname@email.com", false, "photoUrl", false);
        WPUserResponse foundUserResponse =
                new WPUserResponse("id", "uname", "fname.lname@email.com", false, "photoUrl", false);;
        when(wpUserRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(foundUser));
        WPUserResponse receivedResponse = wpUserService.getUserById(id);
        assertEquals(foundUserResponse.getId(), receivedResponse.getId());
        assertEquals(foundUserResponse.getUserName(), receivedResponse.getUserName());
        assertEquals(foundUserResponse.getEmail(), receivedResponse.getEmail());
    }

    @Test
    public void testGetUserByIdDoesNotExists() {
        when(wpUserRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> wpUserService.getUserById(id));
    }
}
