package com.jb.bookingSystem.service;

import com.jb.bookingSystem.api.dto.MemberDto;

import java.util.Optional;

public interface MemberService {

    Optional<MemberDto> getMember(Integer id);

    Optional<MemberDto> getMemberByName(String name);
}
