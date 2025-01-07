package com.example.toucheese_be.domain.studio.dto.response;

import com.example.toucheese_be.domain.studio.dto.ReviewDto;
import com.example.toucheese_be.domain.user.dto.response.UserProfileDto;
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
public class ReviewDetailDto {
    private ReviewDto reviewDto;
    private UserProfileDto userProfileDto;
}
