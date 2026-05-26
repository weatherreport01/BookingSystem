package com.jb.bookingSystem.service.impl;

import com.jb.bookingSystem.api.CreateMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.mapper.MemberMapper;
import com.jb.bookingSystem.mapper.impl.MemberMapperImpl;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.repository.MemberRepository;
import com.jb.bookingSystem.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper){
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    // this one might be a bit useless
    public Optional<MemberEntity> getMember(Integer id){
        return memberRepository.findById(id);
    }

    public Optional<MemberEntity> getMemberByName(String name){
        return memberRepository.findByName(name);
    }

    public MemberEntity createMember(CreateMemberRequest memberRequest){
        // add a db check to prevent two accounts with same email
        MemberEntity memberEntity = memberMapper.fromDto(memberRequest);
        return memberRepository.save(memberEntity);
    }
    public MemberEntity updateMember(String email, UpdateMemberRequest updateMemberRequest){
        MemberEntity member = memberRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("Member Not Found"));
        memberMapper.fromDto(member,updateMemberRequest);
        return memberRepository.save(member);
    }

}
