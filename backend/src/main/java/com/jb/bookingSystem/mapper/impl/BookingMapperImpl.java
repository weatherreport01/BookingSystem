package com.jb.bookingSystem.mapper.impl;


import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.api.dto.BookingDto;
import com.jb.bookingSystem.mapper.BookingMapper;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.BookingStatus;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingEntity fromDto(MemberEntity member, RoomEntity room, CreateBookingRequest bookingRequest) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setMember(member);
        bookingEntity.setRoom(room);
        bookingEntity.setCheckInDate(bookingRequest.checkInDate());
        bookingEntity.setCheckOutDate(bookingRequest.checkOutDate());
        bookingEntity.setStatus(BookingStatus.PENDING);
        return bookingEntity;
    }

    @Override
    public void fromDto(BookingEntity booking, UpdateBookingRequest bookingRequest) {
        booking.setCheckInDate(bookingRequest.checkInDate());
        booking.setCheckOutDate(bookingRequest.checkOutDate());
        booking.setStatus(bookingRequest.status());
    }

    @Override
    public BookingDto toDto(BookingEntity bookingEntity) {
        return new BookingDto(
                bookingEntity.getId(),
                bookingEntity.getRoom().getRoomNumber(),
                bookingEntity.getMember().getId(),
                bookingEntity.getCheckInDate(),
                bookingEntity.getCheckOutDate(),
                bookingEntity.getStatus()
        );
    }
}
