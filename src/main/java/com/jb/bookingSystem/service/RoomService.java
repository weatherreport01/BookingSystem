package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.persistence.entity.RoomType;

import java.util.Optional;

public interface RoomService {

    Optional<RoomDto> getRoomByRoomNumber(int roomNumber);

    Optional<RoomDto> getRoomsByType(RoomType type);
}
