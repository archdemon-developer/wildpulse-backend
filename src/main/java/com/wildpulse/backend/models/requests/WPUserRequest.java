package com.wildpulse.backend.models.requests;

import com.wildpulse.backend.constants.WPDefaultConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class WPUserRequest {

    private String id;

    private String userName;

    @Email(message = WPDefaultConstants.WP_MESSAGE_PROPERTY_EMAIL_INVALID_ERROR)
    @NotBlank(message = WPDefaultConstants.WP_MESSAGE_PROPERTY_EMAIL_NOTBLANK_ERROR)
    private String email;

    private boolean emailVerified;

    private String photoUrl;

    private boolean isSubscribed;
}
