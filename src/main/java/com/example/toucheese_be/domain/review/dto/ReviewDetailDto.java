package com.example.toucheese_be.domain.review.dto;

import com.example.toucheese_be.domain.auth.user.dto.UserProfileDto;
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
