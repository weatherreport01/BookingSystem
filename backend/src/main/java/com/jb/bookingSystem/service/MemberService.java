package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.api.CreateMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.persistence.entity.MemberEntity;

import java.util.Optional;
import java.util.UUID;

public interface MemberService {
    // returns entities but will convert to a dto in the controller layer
    Optional<MemberEntity> getMember(UUID id);

    Optional<MemberEntity> getMemberByName(String name);

    void createMember(CreateMemberRequest memberRequest);

    MemberEntity updateMember(String email,UpdateMemberRequest updateMemberRequest);
}
