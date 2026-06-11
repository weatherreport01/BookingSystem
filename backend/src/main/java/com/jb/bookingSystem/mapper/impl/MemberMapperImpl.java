package com.jb.bookingSystem.mapper.impl;

import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
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
                member.getEmail()
        );
    }

    public MemberEntity fromDto(AuthMemberRequest memberRequest){
        MemberEntity member = new MemberEntity();
        member.setName(memberRequest.name());
        member.setEmail(memberRequest.email());
        // need to hash the password
        return member;
    }

    public void fromDto(MemberEntity member, UpdateMemberRequest updateMemberRequest){
        member.setName(updateMemberRequest.name());
        member.setEmail(updateMemberRequest.email());
        // need to hash new password
    }
}
