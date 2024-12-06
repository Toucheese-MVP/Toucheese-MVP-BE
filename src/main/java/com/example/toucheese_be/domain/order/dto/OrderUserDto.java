package com.example.toucheese_be.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderUserDto {
    // 사용자 ID
    private Long userId;
    // 사용자 이름
    private String userName;
    // 사용자 이메일
    private String userEmail;
    // 사용자 연락처
    private String userPhone;
}
