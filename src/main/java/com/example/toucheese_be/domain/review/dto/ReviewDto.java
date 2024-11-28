package com.example.toucheese_be.domain.review.dto;

import com.example.toucheese_be.domain.review.entity.Review;

import com.example.toucheese_be.domain.review.entity.ReviewImageEntity;
import com.example.toucheese_be.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private List<String> imageUrl;
    private String description;
    private String userProfileName;
    private String userProfileImage;

    public static ReviewDto fromEntity(Review entity){
        return ReviewDto.builder()
                .id(entity.getId())
                .imageUrl(entity.getReviewImage()
                        .stream()
                        .map(ReviewImageEntity::getImageUrl)
                        .collect(Collectors.toList()))
                .description(entity.getDescription())
                .build();
    }
}
