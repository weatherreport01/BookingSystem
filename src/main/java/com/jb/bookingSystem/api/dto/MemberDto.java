package com.jb.bookingSystem.api.dto;

public record MemberDto(
        int id,
        String name,
        String email,
        String phoneNumber
) {
}
