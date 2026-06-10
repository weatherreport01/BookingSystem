package com.jb.bookingSystem.api.dto;

import com.jb.bookingSystem.persistence.entity.RoomType;
import java.util.UUID;
public record RoomDto(
        UUID id,
        int roomNumber,
        RoomType type
) {
}
