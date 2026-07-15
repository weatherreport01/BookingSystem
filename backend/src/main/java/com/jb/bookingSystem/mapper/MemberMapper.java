package com.jb.bookingSystem.mapper;

import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.api.CreateMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.persistence.entity.MemberEntity;

import java.util.List;

public interface MemberMapper {

    MemberDto toDto(MemberEntity member);
    List<MemberDto> toDto(List<MemberEntity> memberEntities);
    MemberEntity fromDto(CreateMemberRequest memberRequest);
    void fromDto(MemberEntity member, UpdateMemberRequest updateMemberRequest);
}
