package com.jb.bookingSystem.mapper;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.api.dto.BookingDto;
import com.jb.bookingSystem.persistence.entity.BookingEntity;

public interface BookingMapper {
    BookingEntity fromDto(CreateBookingRequest bookingRequest);
    void fromDto(BookingEntity booking, UpdateBookingRequest bookingRequest);
    BookingDto toDto(BookingEntity bookingEntity);
}
