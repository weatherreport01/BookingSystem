package com.jb.bookingSystem.mapper.impl;

import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.api.CreateMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.mapper.MemberMapper;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberMapperImpl implements MemberMapper {
    private final PasswordEncoder encoder;


    public MemberMapperImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public MemberDto toDto(MemberEntity member) {
        return new MemberDto(
                member.getName(),
                member.getEmail()
        );
    }

    public MemberEntity fromDto(CreateMemberRequest memberRequest){
        MemberEntity member = new MemberEntity();
        member.setName(memberRequest.name());
        member.setEmail(memberRequest.email());
        member.setPassword(encoder.encode(memberRequest.password()));
        return member;
    }

    public void fromDto(MemberEntity member, UpdateMemberRequest updateMemberRequest){
        if(updateMemberRequest.name() != null){
            member.setName(updateMemberRequest.name());
        }

        if(updateMemberRequest.email() != null) {
            member.setEmail(updateMemberRequest.email());
        }
        if(updateMemberRequest.password() != null){
            member.setPassword(encoder.encode(updateMemberRequest.password()));
        }
    }
}
