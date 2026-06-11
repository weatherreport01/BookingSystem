package com.jb.bookingSystem.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthMemberRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
