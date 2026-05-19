package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.api.dto.BookingDto;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    Optional<BookingEntity> createBooking(CreateBookingRequest request);

    Optional<BookingEntity> getBookingById(int bookingId);

    Optional<BookingEntity> getBookingByMember(int memberId);

    void cancelBooking(int bookingId);

    Optional<BookingEntity> updateBooking(int bookingId, UpdateBookingRequest request);

    List<RoomEntity> getAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate);

}
