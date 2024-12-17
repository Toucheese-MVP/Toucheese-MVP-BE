package com.example.toucheese_be.domain.studio.controller;

import com.example.toucheese_be.domain.studio.dto.response.ReviewDetailDto;
import com.example.toucheese_be.domain.studio.service.ReviewService;
import com.example.toucheese_be.domain.studio.dto.StudioReviewDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studio")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 스튜디오 상세 조회 - 리뷰탭
     */
    @GetMapping("/{studioId}/reviews")
    private ResponseEntity<StudioReviewDetailDto> getStudioReviews(
            @PathVariable
            Long studioId
    ) {
      return reviewService.getStudioReviews(studioId);
    }

    /**
     * 스튜디오 리뷰 상세 조회
     */
    @GetMapping("/review/{reviewId}/details")
    private ResponseEntity<ReviewDetailDto> getReviewDetail(
            @PathVariable
            Long reviewId
    ) {
        return reviewService.getReviewDetails(reviewId);
    }

}
