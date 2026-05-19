package com.jb.bookingSystem.api;

import java.time.LocalDateTime;

public record UpdateBookingRequest(
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate
) {
}
