package com.example.toucheese_be.domain.user.dto.request;

import lombok.Getter;

@Getter
public class UpdateUserDto {
    private String username;
    private String email;
    private String phone;
    private String nickname;
}
