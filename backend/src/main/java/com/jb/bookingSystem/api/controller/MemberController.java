package com.jb.bookingSystem.api.controller;


import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.api.UpdateMemberRequest;
import com.jb.bookingSystem.api.dto.MemberDto;
import com.jb.bookingSystem.mapper.MemberMapper;
import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;


    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity<MemberDto> searchForMember(@PathVariable String name){
        MemberEntity member = memberService.getMemberByName(name).orElseThrow(); // handle this later
        MemberDto response = memberMapper.toDto(member);
        return ResponseEntity.ok(response);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<MemberDto> updateMember(@RequestBody UpdateMemberRequest request){
        MemberEntity member = memberService.updateMember(request.email(), request);
        MemberDto response = memberMapper.toDto(member);
        return ResponseEntity.ok(response);

    }
}
