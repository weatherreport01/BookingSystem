package com.jb.bookingSystem.persistence.repository;

import com.jb.bookingSystem.persistence.entity.BookingEntity;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Integer> {
    Optional<BookingEntity> findByMemberName(String name);
}
