package com.jb.bookingSystem.service.impl;

import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.mapper.MemberMapper;
import com.jb.bookingSystem.mapper.impl.MemberMapperImpl;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.repository.MemberRepository;
import com.jb.bookingSystem.service.MemberService;
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

    public Optional<MemberDto> getMember(Integer id){
        Optional<MemberEntity> memberFromDB = memberRepository.findById(id);
        return memberFromDB.map(memberMapper::toDto);
    }


}
