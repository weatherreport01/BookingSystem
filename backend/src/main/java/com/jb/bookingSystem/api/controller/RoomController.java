package com.jb.bookingSystem.api.controller;

import com.jb.bookingSystem.api.CreateRoomRequest;
import com.jb.bookingSystem.api.UpdateRoomRequest;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.mapper.RoomMapper;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.entity.RoomType;
import com.jb.bookingSystem.persistence.repository.RoomRepository;
import com.jb.bookingSystem.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;


    public RoomController(RoomService roomService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }
    @PreAuthorize("hasRole('STAFF','ADMIN')")
    @GetMapping(path = "/searchByType")
    public ResponseEntity<List<RoomDto>> getRoomsByType(@RequestBody RoomType type){
        List<RoomEntity> roomsOfType = roomService.getRoomsByType(type);
        List<RoomDto> response = new ArrayList<>();
        for(RoomEntity room : roomsOfType){
            response.add(roomMapper.toDto(room));
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/searchByRoomNumber")
    public ResponseEntity<RoomDto> getRoomByNumber(@RequestBody int roomNumber){
        RoomEntity room = roomService.getRoomByRoomNumber(roomNumber).orElseThrow(); // do something about this later
        RoomDto response = roomMapper.toDto(room);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody CreateRoomRequest request){
        RoomEntity room = roomService.createRoom(request);
        return new ResponseEntity<>(roomMapper.toDto(room), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/update")
    public ResponseEntity<RoomDto> updateRoom(@Valid @RequestBody UpdateRoomRequest request){
        RoomEntity room = roomService.updateRoom(request);
        RoomDto response = roomMapper.toDto(room);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Void> deleteRoom(@RequestBody int roomNumber){
        roomService.deleteRoom(roomNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
