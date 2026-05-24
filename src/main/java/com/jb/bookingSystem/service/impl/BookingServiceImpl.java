package com.jb.bookingSystem.service.impl;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.mapper.BookingMapper;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.BookingStatus;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.repository.BookingRepository;
import com.jb.bookingSystem.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;


    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper){
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    public BookingEntity createBooking(CreateBookingRequest request){
        BookingEntity bookingEntity = bookingMapper.fromDto(request);
        return bookingRepository.save(bookingEntity);
    }

    public Optional<BookingEntity> getBookingById(int bookingId){return bookingRepository.findById(bookingId);}

    public Optional<BookingEntity> getBookingByMember(String memberName){return bookingRepository.findByMemberName(memberName);}

    public void cancelBooking(int bookingId){
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(()->new EntityNotFoundException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    public BookingEntity updateBooking(int bookingId, UpdateBookingRequest request){
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(()->new EntityNotFoundException("Booking not found"));
        bookingMapper.fromDto(booking,request);
        return booking;
    }

    public List<RoomEntity> getAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate){
        return bookingRepository.findAvailableRooms(checkInDate,checkOutDate);
    }

}
