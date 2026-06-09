package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.CreateRoomRequest;
import com.jb.bookingSystem.api.UpdateRoomRequest;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.entity.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    Optional<RoomEntity> getRoomByRoomNumber(int roomNumber);

    List<RoomEntity> getRoomsByType(RoomType type);

    RoomEntity createRoom(CreateRoomRequest createRoomRequest);

    RoomEntity updateRoom(UpdateRoomRequest updateRoomRequest);

    void deleteRoom(int roomNumber);
}
