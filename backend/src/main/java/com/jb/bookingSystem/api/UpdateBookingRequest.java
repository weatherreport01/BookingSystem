package com.jb.bookingSystem.api;

import com.jb.bookingSystem.persistence.entity.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateBookingRequest(
        UUID id,
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate,
        BookingStatus status
) {
}
