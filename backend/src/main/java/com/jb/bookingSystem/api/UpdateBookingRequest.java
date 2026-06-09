package com.jb.bookingSystem.api;

import com.jb.bookingSystem.persistence.entity.BookingStatus;

import java.time.LocalDateTime;

public record UpdateBookingRequest(
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate,
        BookingStatus status
) {
}
