package com.jb.bookingSystem.api.controller;


import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.mapper.MemberMapper;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;


    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PutMapping(path = "/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MemberDto> updateMember(@RequestBody UpdateMemberRequest request, Authentication authentication){
        String email = authentication.getName(); // gets the email not name
        MemberEntity member = memberService.updateMember(email, request);
        MemberDto response = memberMapper.toDto(member);
        return ResponseEntity.ok(response);

    }

    // NEED TO ADD A DELETE ENDPOINT
}
