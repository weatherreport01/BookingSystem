package com.jb.bookingSystem.mapper.impl;

import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.mapper.MemberMapper;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDto toDto(MemberEntity member) {
        return new MemberDto(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber()
        );
    }
    // will add more later
}
