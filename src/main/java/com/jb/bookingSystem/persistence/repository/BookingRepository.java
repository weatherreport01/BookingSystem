package com.jb.bookingSystem.persistence.repository;

import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity,Integer> {
    Optional<BookingEntity> findByMemberName(String name);
}
