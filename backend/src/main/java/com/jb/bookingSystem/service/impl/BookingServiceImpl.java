package com.jb.bookingSystem.service.impl;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.mapper.BookingMapper;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.BookingStatus;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.repository.BookingRepository;
import com.jb.bookingSystem.persistence.repository.MemberRepository;
import com.jb.bookingSystem.persistence.repository.RoomRepository;
import com.jb.bookingSystem.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper, MemberRepository memberRepository,
                              RoomRepository roomRepository){
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.memberRepository = memberRepository;
        this.roomRepository = roomRepository;
    }

    public BookingEntity createBooking(CreateBookingRequest request){
        MemberEntity member = memberRepository.findById(request.memberId()).orElseThrow(()->new EntityNotFoundException("Member not found"));
        RoomEntity room = roomRepository.findById(request.roomId()).orElseThrow(()->new EntityNotFoundException("Room not found"));
        BookingEntity bookingEntity = bookingMapper.fromDto(member,room,request);
        return bookingRepository.save(bookingEntity);
    }

    public Optional<BookingEntity> getBookingById(UUID bookingId){return bookingRepository.findById(bookingId);}

    public Optional<BookingEntity> getBookingByMember(String memberName){return bookingRepository.findByMemberName(memberName);}

    public void cancelBooking(UUID bookingId){
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(()->new EntityNotFoundException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    public BookingEntity updateBooking(UUID bookingId, UpdateBookingRequest request){
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(()->new EntityNotFoundException("Booking not found"));
        bookingMapper.fromDto(booking,request);
        return bookingRepository.save(booking);
    }

    public List<RoomEntity> getAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate){
        return bookingRepository.findAvailableRooms(checkInDate,checkOutDate);
    }

}
