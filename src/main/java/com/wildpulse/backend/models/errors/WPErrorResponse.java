package com.wildpulse.backend.models.errors;

import java.util.List;

public record WPErrorResponse(List<WPErrorDetails> errors) {}
