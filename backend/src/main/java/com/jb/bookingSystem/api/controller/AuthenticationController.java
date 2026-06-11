package com.jb.bookingSystem.api.controller;

import com.jb.bookingSystem.api.AuthMemberRequest;
import com.jb.bookingSystem.persistence.repository.MemberRepository;
import com.jb.bookingSystem.security.JwtUtil;
import com.jb.bookingSystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private MemberRepository memberRepository;
    private PasswordEncoder encoder;
    private JwtUtil jwtUtil;
    private MemberService memberService;
    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
    MemberRepository memberRepository, PasswordEncoder encoder, JwtUtil jwtUtil, MemberService memberService) {

        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.memberService = memberService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthMemberRequest memberRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        memberRequest.email(),
                        memberRequest.password()
                )
        );
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(token);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody AuthMemberRequest memberRequest){
        if (memberRepository.existsByEmail(memberRequest.email())){
            return ResponseEntity.status(409).body("This account already exists!");
        }
        memberService.createMember(memberRequest);
        return ResponseEntity.status(201).body("User registered!");
    }
}
