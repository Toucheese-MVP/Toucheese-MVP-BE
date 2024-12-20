package com.example.toucheese_be.domain.order.dto;

import com.example.toucheese_be.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
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

//    public static OrderUserDto fromEntity(User user) {
//        return OrderUserDto.builder()
//                .userId(user.getId())
//                .userName(user.getNickname())
//                .userPhone(user.getPhone())
//                .userEmail(user.getEmail())
//                .build();
//    }
}
