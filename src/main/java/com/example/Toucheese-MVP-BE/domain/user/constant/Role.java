package com.example.toucheese_be.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    // 비회원
    GUEST ("ROLE_GUEST"),
    // 회원
    MEMBER ("ROLE_GUEST,ROLE_MEMBER"),
    // 관리자
    ADMIN ("ROLE_GUEST,ROLE_MEMBER,ROLE_ADMIN");

    private final String roles;
}