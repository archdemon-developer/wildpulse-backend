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

    @Test
    public void testCreateUserMethodSuccessfulCreation() {
        when(wpUserRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        WPUserRequest userToCreate =
                new WPUserRequest("uname", "fname", "lname", "fname.lname@email.com");
        WPUser createdUser =
                new WPUser(
                        1,
                        userToCreate.firstName() + " " + userToCreate.lastName(),
                        userToCreate.userName(),
                        userToCreate.email());
        WPUserResponse createdUserResponse =
                new WPUserResponse(
                        createdUser.getId(),
                        createdUser.getName(),
                        createdUser.getUserName(),
                        createdUser.getEmail());
        when(wpUserRepository.save(ArgumentMatchers.any(WPUser.class))).thenReturn(createdUser);
        WPUserResponse receivedResponse = wpUserService.createUser(userToCreate);
        assertEquals(createdUserResponse.id(), receivedResponse.id());
        assertEquals(createdUserResponse.userName(), receivedResponse.userName());
        assertEquals(createdUserResponse.email(), receivedResponse.email());
        assertEquals(createdUserResponse.name(), receivedResponse.name());
    }

    @Test
    public void testCreateUserAlreadyExists() {
        WPUserRequest userToCreate =
                new WPUserRequest("uname", "fname", "lname", "fname.lname@email.com");
        WPUser existingUser =
                new WPUser(
                        1,
                        userToCreate.firstName() + " " + userToCreate.lastName(),
                        userToCreate.userName(),
                        userToCreate.email());
        when(wpUserRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(existingUser));
        assertThrows(
                DataIntegrityViolationException.class,
                () -> wpUserService.createUser(userToCreate));
    }

    @Test
    public void testGetUserById() {
        long userId = 1L;
        WPUser foundUser = new WPUser(1, "uname", "fname lname", "fname.lname@email.com");
        WPUserResponse foundUserResponse =
                new WPUserResponse(
                        foundUser.getId(),
                        foundUser.getName(),
                        foundUser.getUserName(),
                        foundUser.getEmail());
        when(wpUserRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(foundUser));
        WPUserResponse receivedResponse = wpUserService.getUserById(userId);
        assertEquals(foundUserResponse.id(), receivedResponse.id());
        assertEquals(foundUserResponse.userName(), receivedResponse.userName());
        assertEquals(foundUserResponse.email(), receivedResponse.email());
        assertEquals(foundUserResponse.name(), receivedResponse.name());
    }

    @Test
    public void testGetUserByIdDoesNotExists() {
        long userId = 1L;
        when(wpUserRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> wpUserService.getUserById(userId));
    }
}
