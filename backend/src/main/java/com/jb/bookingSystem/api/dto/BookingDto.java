package com.jb.bookingSystem.api.dto;
import com.jb.bookingSystem.persistence.entity.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;
public record BookingDto(
        UUID id,
        int roomNumber,
        UUID memberId,
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate,
        BookingStatus status
) {
}
