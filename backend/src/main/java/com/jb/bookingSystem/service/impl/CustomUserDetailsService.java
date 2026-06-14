package com.jb.bookingSystem.service.impl;

import com.jb.bookingSystem.persistence.entity.MemberEntity;
import com.jb.bookingSystem.persistence.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Autowired

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // NOTE THIS ACTUALLY NEEDS AN EMAIL INSTEAD OF A USERNAME
    // THERE ISNT A METHOD WITH USERDETAILSSERVICE THAT USES AN EMAIL
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Member not found!"));
        return new User(
                member.getEmail(),
                member.getPassword(),
                Set.of(new SimpleGrantedAuthority(member.getRole().name()))
        );
    }
}
