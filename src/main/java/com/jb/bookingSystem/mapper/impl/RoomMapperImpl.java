package com.jb.bookingSystem.mapper.impl;

import com.jb.bookingSystem.api.CreateRoomRequest;
import com.jb.bookingSystem.api.UpdateRoomRequest;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.mapper.RoomMapper;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDto toDto(RoomEntity roomEntity) {
        return new RoomDto(
                roomEntity.getId(),
                roomEntity.getRoomNumber(),
                roomEntity.getType()
        );
    }

    @Override
    public RoomEntity fromDto(CreateRoomRequest createRoomRequest) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber(createRoomRequest.roomNumber());
        roomEntity.setType(createRoomRequest.roomtype());
        return roomEntity;
    }

    @Override
    public void fromDto(RoomEntity room, UpdateRoomRequest updateRoomRequest) {
        room.setRoomNumber(updateRoomRequest.roomNumber());
        room.setType(updateRoomRequest.roomType());
    }
}
