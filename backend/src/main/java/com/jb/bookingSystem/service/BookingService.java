package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.api.dto.BookingDto;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingService {

    BookingEntity createBooking(UUID memberId, CreateBookingRequest request);

    Optional<BookingEntity> getBookingById(UUID bookingId);

    Optional<BookingEntity> getBookingByMember(String memberName);

    void cancelBooking(UUID bookingId);

    BookingEntity updateBooking(UUID bookingId, UpdateBookingRequest request);

    List<RoomEntity> getAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate);

}
