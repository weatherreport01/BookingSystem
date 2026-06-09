package com.jb.bookingSystem.api;

import com.jb.bookingSystem.persistence.entity.RoomType;
import jakarta.validation.constraints.NotBlank;

public record CreateRoomRequest(
        @NotBlank int roomNumber,
        @NotBlank RoomType roomtype
) {
}
