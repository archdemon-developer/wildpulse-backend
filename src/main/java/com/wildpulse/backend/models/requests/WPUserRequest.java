package com.wildpulse.backend.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
public record WPUserRequest(
        String userName,
        @Pattern(regexp = "^[A-Za-z]+$", message = "{wp.firstname.pattern.msg}") String firstName,
        @Pattern(regexp = "^[A-Za-z]+$", message = "{wp.lastname.pattern.msg}") String lastName,
        @Email(message = "{wp.email.invalid.msg}") @NotBlank(message = "{wp.email.notblank.msg}")
                String email) {}
