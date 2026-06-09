package com.jb.bookingSystem.api;

import com.jb.bookingSystem.persistence.entity.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRoomRequest(
        @NotNull @NotBlank int roomNumber,
        RoomType roomType
) {
}
