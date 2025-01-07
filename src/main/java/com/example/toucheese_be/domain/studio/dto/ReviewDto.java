package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.Review;

import com.example.toucheese_be.domain.studio.entity.ReviewImage;
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
// 리뷰 상세 페이지
public class ReviewDto {
    private Long id;
    private List<String> imageUrl;
    private String description;

    public static ReviewDto fromEntity(Review entity){
        return ReviewDto.builder()
                .id(entity.getId())
                .imageUrl(entity.getReviewImage()
                        .stream()
                        .map(ReviewImage::getImageUrl)
                        .collect(Collectors.toList()))
                .description(entity.getDescription())
                .build();
    }
}
