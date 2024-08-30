package com.wildpulse.backend.models.errors;

import com.wildpulse.backend.exceptions.enums.WPErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WPErrorDetails {
    private WPErrorCode errorCode;
    private String message;
}
