package com.jb.bookingSystem.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateBookingRequest(
       @NotBlank UUID roomId,
       @NotBlank @NotNull LocalDateTime checkInDate,
       @NotBlank @NotNull LocalDateTime checkOutDate
) {
}
