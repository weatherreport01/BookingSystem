package com.jb.bookingSystem.api.controller;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.api.dto.BookingDto;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.mapper.BookingMapper;
import com.jb.bookingSystem.mapper.RoomMapper;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.repository.MemberRepository;
import com.jb.bookingSystem.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final RoomMapper roomMapper;
    private final MemberRepository memberRepository;

    public BookingController(BookingService bookingService, BookingMapper bookingMapper,RoomMapper roomMapper, MemberRepository memberRepository) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.roomMapper = roomMapper;
        this.memberRepository = memberRepository;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/availableRooms")
    public ResponseEntity<List<RoomDto>> getAllAvailableRooms(
            @RequestParam LocalDateTime checkInDate,
            @RequestParam LocalDateTime checkOutDate){
            List<RoomEntity> availableRooms = bookingService.getAvailableRooms(checkInDate,checkOutDate);
            List<RoomDto> response = new ArrayList<>();
            for(RoomEntity room : availableRooms){
                response.add(roomMapper.toDto(room));
            }
            return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/book")
    public ResponseEntity<BookingDto> createBooking(
            @Valid @RequestBody CreateBookingRequest request,
            Authentication authentication){
            String email = authentication.getName();
            UUID member = memberRepository.findByEmail(email).map(MemberEntity::getId).orElseThrow(() -> new EntityNotFoundException("Member not found!"));
            BookingEntity booking = bookingService.createBooking(member,request);
            BookingDto response = bookingMapper.toDto(booking);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "/update")
    public ResponseEntity<BookingDto> updateBooking(
            @Valid @RequestBody UpdateBookingRequest request,
            Authentication authentication){
            String email = authentication.getName();
            UUID bookingId = request.id();
            MemberEntity member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Member not found!"));
            BookingEntity booking = bookingService.getBookingById(bookingId).orElseThrow(()-> new EntityNotFoundException("Booking not found!"));
            if(!booking.getMember().equals(member)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            BookingEntity bookingEntity = bookingService.updateBooking(request);
            BookingDto response = bookingMapper.toDto(bookingEntity);
            return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(path = "/cancel")
    public ResponseEntity<Void> cancelBooking(
            @RequestBody UUID bookingId,
            Authentication authentication){
            String email = authentication.getName();
            MemberEntity member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Member not found!"));
            BookingEntity booking = bookingService.getBookingById(bookingId).orElseThrow(()-> new EntityNotFoundException("Booking not found!"));
            if(!booking.getMember().equals(member)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            bookingService.cancelBooking(bookingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/currentBookings")
    public ResponseEntity<List<BookingDto>> getUserBookings(
            Authentication authentication){
            String email = authentication.getName();
            List<BookingDto> bookings = bookingService.getUserBookings(email);
            return ResponseEntity.ok(bookings);
    }

}
