package com.jb.bookingSystem.persistence.repository;

import com.jb.bookingSystem.persistence.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByName(String name);
    Optional<MemberEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
