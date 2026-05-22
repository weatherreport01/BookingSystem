package com.jb.bookingSystem.api;

import com.jb.bookingSystem.persistence.entity.RoomType;

public record UpdateRoomRequest(
        int roomNumber,
        RoomType roomType
) {
}
