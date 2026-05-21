package com.jb.bookingSystem.api;

public record UpdateMemberRequest(
        String name,
        String email,
        String phoneNumber
) {
}
