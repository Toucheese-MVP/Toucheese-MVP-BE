package com.example.toucheese_be.domain.reserve.dto;

import com.example.toucheese_be.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservedUserDto {
    private Long id;
    private String userName;
    private String email;
    private String phoneNumber;

}
