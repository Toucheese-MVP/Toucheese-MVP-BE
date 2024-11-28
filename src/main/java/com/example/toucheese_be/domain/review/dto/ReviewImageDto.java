package com.example.toucheese_be.domain.review.dto;

import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.review.entity.Review;
import com.example.toucheese_be.domain.review.entity.ReviewImage;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImageDto {
    private Long reviewId;
    private String imageUrl;

    public static List<ReviewImageDto> fromEntity(Item item) {
        List<ReviewImageDto> reviewImageDtos = new ArrayList<>();

        if (item.getItemReview() != null && !item.getItemReview().isEmpty()) {
            for (Review review : item.getItemReview()) {
                if (review.getReviewImage() != null && !review.getReviewImage().isEmpty()) {
                    ReviewImage firstImage = review.getReviewImage().get(0); // 리뷰의 첫 번째 이미지
                    reviewImageDtos.add(
                            ReviewImageDto.builder()
                                    .reviewId(review.getId()) // 리뷰 ID
                                    .imageUrl(firstImage.getImageUrl()) // 첫 번째 이미지 URL
                                    .build()
                    );
                }
            }
        }
        return reviewImageDtos;

    }
}
