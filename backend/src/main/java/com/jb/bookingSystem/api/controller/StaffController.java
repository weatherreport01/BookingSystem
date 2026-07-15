package com.jb.bookingSystem.api.controller;


import com.jb.bookingSystem.api.CreateRoomRequest;
import com.jb.bookingSystem.api.UpdateRoomRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.mapper.MemberMapper;
import com.jb.bookingSystem.mapper.RoomMapper;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.entity.RoomType;
import com.jb.bookingSystem.service.MemberService;
import com.jb.bookingSystem.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    public StaffController(MemberService memberService, MemberMapper memberMapper, RoomService roomService, RoomMapper roomMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }


    @GetMapping(path = "/member/search")
    @PreAuthorize("hasRole('STAFF','ADMIN')")
    public ResponseEntity<List<MemberDto>> searchForMember(@RequestParam String name){
        List<MemberEntity> member = memberService.getMemberByName(name); // handle this later
        List<MemberDto> response = memberMapper.toDto(member);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('STAFF','ADMIN')")
    @GetMapping(path = "/rooms/searchByType/{type}")
    public ResponseEntity<List<RoomDto>> getRoomsByType(@PathVariable RoomType type){
        List<RoomEntity> roomsOfType = roomService.getRoomsByType(type);
        List<RoomDto> response = new ArrayList<>();
        for(RoomEntity room : roomsOfType){
            response.add(roomMapper.toDto(room));
        }
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('STAFF','ADMIN')")
    @GetMapping(path = "/rooms/searchByRoomNumber")
    public ResponseEntity<RoomDto> getRoomByNumber(@RequestParam int roomNumber){
        RoomEntity room = roomService.getRoomByRoomNumber(roomNumber).orElseThrow(); // do something about this later
        RoomDto response = roomMapper.toDto(room);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/rooms/create")
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody CreateRoomRequest request){
        RoomEntity room = roomService.createRoom(request);
        return new ResponseEntity<>(roomMapper.toDto(room), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/rooms/update")
    public ResponseEntity<RoomDto> updateRoom(@Valid @RequestBody UpdateRoomRequest request){
        RoomEntity room = roomService.updateRoom(request);
        RoomDto response = roomMapper.toDto(room);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/rooms/delete")
    public ResponseEntity<Void> deleteRoom(@RequestBody int roomNumber){
        roomService.deleteRoom(roomNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/rooms/type")
    public ResponseEntity<RoomType[]> getRoomTypes(){
        return ResponseEntity.ok(RoomType.values());
    }

    // NEED TO ADD BOOKING MANAGEMENT ENDPOINTS FOR STAFF MEMBERS
}
