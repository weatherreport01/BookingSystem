package com.jb.bookingSystem.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateBookingRequest(
       @NotBlank int roomId,
       @NotBlank @NotNull int memberId,
       @NotBlank @NotNull LocalDateTime checkInDate,
       @NotBlank @NotNull LocalDateTime checkOutDate
) {
}
