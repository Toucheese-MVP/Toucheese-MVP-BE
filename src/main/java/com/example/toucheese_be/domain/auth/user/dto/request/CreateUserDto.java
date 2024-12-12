package com.example.toucheese_be.domain.auth.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordCheck;
}
