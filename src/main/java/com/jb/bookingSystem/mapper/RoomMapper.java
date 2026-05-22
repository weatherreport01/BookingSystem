package com.jb.bookingSystem.mapper;

import com.jb.bookingSystem.api.CreateRoomRequest;
import com.jb.bookingSystem.api.UpdateRoomRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;

public interface RoomMapper {

    RoomDto toDto(MemberEntity member);

    RoomEntity fromDto(CreateRoomRequest createRoomRequest);

    RoomEntity fromDto(UpdateRoomRequest updateRoomRequest);
}
