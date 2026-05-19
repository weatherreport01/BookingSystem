package com.jb.bookingSystem.api.dto;

import com.jb.bookingSystem.persistence.entity.RoomType;

public record RoomDto(
        int id,
        int roomNumber,
        RoomType type
) {
}
