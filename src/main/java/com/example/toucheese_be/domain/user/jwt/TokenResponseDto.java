package com.example.toucheese_be.domain.user.jwt;



import com.example.toucheese_be.domain.user.dto.response.UserDto;
import java.util.Date;
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
public class TokenResponseDto {
    private String accessToken;
    private Date issuedAt;
    private Date expiration;
    private String refreshToken;
}
