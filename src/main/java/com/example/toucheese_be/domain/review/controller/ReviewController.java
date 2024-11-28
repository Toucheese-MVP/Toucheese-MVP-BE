package com.example.toucheese_be.domain.review.controller;

import com.example.toucheese_be.domain.review.dto.ReviewDetailDto;
import com.example.toucheese_be.domain.review.dto.ReviewDto;
import com.example.toucheese_be.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/review")
    private ResponseEntity<List<ReviewDto>> getReview() {

        return ResponseEntity.ok(reviewService.getReview());
    }

    // 리뷰상세페이지
    @GetMapping("/review/{reviewId}")
    private ResponseEntity<ReviewDetailDto> getReviewDetail(
            @PathVariable
            Long reviewId
    ) {
        return ResponseEntity.ok(reviewService.getReviewDetail(reviewId));
    }

}
