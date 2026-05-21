package com.jb.bookingSystem.mapper.impl;

import com.jb.bookingSystem.api.CreateMemberRequest;
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
                member.getEmail(),
                member.getPhoneNumber()
        );
    }

    public MemberEntity fromDto(CreateMemberRequest memberRequest){
        MemberEntity member = new MemberEntity();
        member.setName(memberRequest.name());
        member.setEmail(memberRequest.email());
        member.setPhoneNumber(memberRequest.phoneNumber());
        return member;
    }

    public void fromDto(MemberEntity member, UpdateMemberRequest updateMemberRequest){
        member.setName(updateMemberRequest.name());
        member.setEmail(updateMemberRequest.email());
        member.setPhoneNumber(updateMemberRequest.phoneNumber());
    }
}
