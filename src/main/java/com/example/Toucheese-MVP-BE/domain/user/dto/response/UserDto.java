package com.example.toucheese_be.domain.user.dto.response;

import com.example.toucheese_be.domain.user.constant.Role;
import com.example.toucheese_be.domain.user.constant.SocialProvider;
import com.example.toucheese_be.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;
    private String profileImg;
    private Role role;
    private SocialProvider socialProvider;

    public static UserDto fromEntity(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .nickname(entity.getNickname())
                .phone(entity.getPhone())
                .profileImg(entity.getProfileImg())
                .role(entity.getRole())
                .socialProvider(entity.getSocialProvider())
                .build();
    }
}
