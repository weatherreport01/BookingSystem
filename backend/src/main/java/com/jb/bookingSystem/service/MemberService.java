package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.api.CreateMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.persistence.entity.MemberEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberService {

    Optional<MemberEntity> getMember(UUID id);

    List<MemberEntity> getMemberByName(String name);

    Optional<MemberEntity> getMemberByEmail(String email);

    void createMember(CreateMemberRequest memberRequest);

    MemberEntity updateMember(String email,UpdateMemberRequest updateMemberRequest);

    void staffDeleteMember(String email);
}
