package com.jb.bookingSystem.mapper;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.api.dto.BookingDto;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;

public interface BookingMapper {
    BookingEntity fromDto(MemberEntity member, RoomEntity room, CreateBookingRequest bookingRequest);
    void fromDto(BookingEntity booking, UpdateBookingRequest bookingRequest);
    BookingDto toDto(BookingEntity bookingEntity);
}
