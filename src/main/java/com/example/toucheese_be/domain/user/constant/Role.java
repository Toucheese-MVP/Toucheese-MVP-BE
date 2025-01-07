package com.example.toucheese_be.domain.user.constant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "유저 권한")
public enum Role {
    // 비회원
    @Schema(description = "비회원")
    GUEST ("ROLE_GUEST"),
    // 회원
    @Schema(description = "회원")
    MEMBER ("ROLE_GUEST,ROLE_MEMBER"),
    // 관리자
    @Schema(description = "관리자")
    ADMIN ("ROLE_GUEST,ROLE_MEMBER,ROLE_ADMIN");

    private final String roles;
}