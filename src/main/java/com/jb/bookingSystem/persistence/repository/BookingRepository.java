package com.jb.bookingSystem.persistence.repository;

import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Integer> {
    Optional<BookingEntity> findByMemberName(String name);

    @Query("SELECT r FROM RoomEntity r WHERE r.id NOT IN "+
    "(SELECT DISTINCT b.room.id FROM BookingEntity b WHERE "+
            "b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate AND "+
            "b.status != 'CANCELLED')")
    List<RoomEntity> findAvailableRooms(
            @Param("checkInDate") LocalDateTime checkInDate,
            @Param("checkOutDate") LocalDateTime checkOutDate
    );
}
