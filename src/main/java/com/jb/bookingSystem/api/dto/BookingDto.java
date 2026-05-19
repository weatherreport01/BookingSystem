package com.jb.bookingSystem.api.dto;
import com.jb.bookingSystem.persistence.entity.BookingStatus;

import java.time.LocalDateTime;

public record BookingDto(
        int id,
        int roomNumber,
        int memberId,
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate,
        BookingStatus status
) {
}
