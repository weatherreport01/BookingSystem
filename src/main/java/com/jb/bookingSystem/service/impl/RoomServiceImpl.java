package com.jb.bookingSystem.service.impl;

import com.jb.bookingSystem.api.CreateRoomRequest;
import com.jb.bookingSystem.api.UpdateRoomRequest;
import com.jb.bookingSystem.api.dto.RoomDto;
import com.jb.bookingSystem.mapper.RoomMapper;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.entity.RoomType;
import com.jb.bookingSystem.persistence.repository.RoomRepository;
import com.jb.bookingSystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper){
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public Optional<RoomEntity> getRoomByRoomNumber(int roomNumber){return roomRepository.findByRoomNumber(roomNumber);}

    public List<RoomEntity> getRoomsByType(RoomType type){return roomRepository.findByType(type);}

    public RoomEntity createRoom(CreateRoomRequest createRoomRequest){
        RoomEntity roomEntity = roomMapper.fromDto(createRoomRequest);
        return roomRepository.save(roomEntity);
    }

    public RoomEntity updateRoom(int id, UpdateRoomRequest updateRoomRequest){


    }

}
