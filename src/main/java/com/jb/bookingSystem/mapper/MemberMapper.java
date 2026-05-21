package com.jb.bookingSystem.mapper;

import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.persistence.entity.MemberEntity;

public interface MemberMapper {

    MemberDto toDto(MemberEntity member);

    MemberEntity fromDto(MemberDto member);
}
