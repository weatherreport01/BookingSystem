package com.jb.bookingSystem.persistence.repository;

import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import com.jb.bookingSystem.persistence.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
    Optional<RoomEntity> findByRoomNumber(int roomNumber);

    List<RoomEntity> findByType(RoomType type);
}
