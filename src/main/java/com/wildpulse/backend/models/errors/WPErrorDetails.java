package com.wildpulse.backend.models.errors;

import com.wildpulse.backend.exceptions.enums.WPErrorCode;

public record WPErrorDetails(WPErrorCode errorCode, String message) {}
