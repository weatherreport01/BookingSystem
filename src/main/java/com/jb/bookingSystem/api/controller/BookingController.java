package com.jb.bookingSystem.api.controller;

import com.jb.bookingSystem.api.CreateBookingRequest;
import com.jb.bookingSystem.api.UpdateBookingRequest;
import com.jb.bookingSystem.api.dto.BookingDto;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.mapper.BookingMapper;
import com.jb.bookingSystem.mapper.RoomMapper;
import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    public BookingController(BookingService bookingService, BookingMapper bookingMapper,RoomMapper roomMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.roomMapper = roomMapper;
    }
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

    @PostMapping(path = "/book")
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody CreateBookingRequest request){
       BookingEntity booking = bookingService.createBooking(request);
       BookingDto response = bookingMapper.toDto(booking);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{bookingId}")
    public ResponseEntity<BookingDto> updateBooking(
            @PathVariable UUID bookingId,
            @Valid @RequestBody UpdateBookingRequest request
    ){
        BookingEntity bookingEntity = bookingService.updateBooking(bookingId,request);
        BookingDto response = bookingMapper.toDto(bookingEntity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/remove/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable UUID bookingId){
        bookingService.cancelBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
