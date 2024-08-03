package com.wildpulse.backend.services;

import com.wildpulse.backend.models.requests.WPUserRequest;
import com.wildpulse.backend.models.responses.WPUserResponse;

public interface WPUserService {
    WPUserResponse createUser(WPUserRequest wpUserRequest);

    WPUserResponse getUserById(long userId);
}
