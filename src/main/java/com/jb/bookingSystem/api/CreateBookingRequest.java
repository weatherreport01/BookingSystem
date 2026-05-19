package com.jb.bookingSystem.api;

import java.time.LocalDateTime;

public record CreateBookingRequest(
        int roomId,
        int memberId,
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate
) {
}
