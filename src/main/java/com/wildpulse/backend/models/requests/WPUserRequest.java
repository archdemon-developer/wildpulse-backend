package com.wildpulse.backend.models.requests;

import com.wildpulse.backend.constants.WPDefaultConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class WPUserRequest {
    private String userName;

    @Email(message = WPDefaultConstants.WP_MESSAGE_PROPERTY_EMAIL_INVALID_ERROR)
    @NotBlank(message = WPDefaultConstants.WP_MESSAGE_PROPERTY_EMAIL_NOTBLANK_ERROR)
    private String email;
}
