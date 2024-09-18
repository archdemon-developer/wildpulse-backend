package com.wildpulse.backend.models.responses;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WPUserResponse {
    String id;
    String userName;
    String email;
    boolean emailVerified;
    String photoUrl;
    boolean isSubscribed;
}
