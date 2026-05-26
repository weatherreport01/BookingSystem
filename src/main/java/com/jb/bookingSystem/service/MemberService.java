package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.CreateMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.persistence.entity.MemberEntity;

import java.util.Optional;

public interface MemberService {
    // returns entities but will convert to a dto in the controller layer
    Optional<MemberEntity> getMember(Integer id);

    Optional<MemberEntity> getMemberByName(String name);

    // returns an entity but will convert to a dto in the controller layer
    MemberEntity createMember(CreateMemberRequest memberRequest);

    MemberEntity updateMember(String email,UpdateMemberRequest updateMemberRequest);
}
