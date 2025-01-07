package com.example.toucheese_be.domain.studio.controller;

import com.example.toucheese_be.domain.studio.dto.StudioReviewDetailDto;
import com.example.toucheese_be.domain.studio.dto.response.ReviewDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "03. [리뷰] (토큰 필요 X)", description = "리뷰 관련 API")
public interface ReviewApi {
    /**
     * 스튜디오 상세 조회 - 리뷰탭
     */
    @Operation(summary = "스튜디오 리뷰 조회 (리뷰탭)", description = "스튜디오 상품에 대한 리뷰를 조회합니다.")
    @GetMapping("/{studioId}/reviews")
    ResponseEntity<StudioReviewDetailDto> getStudioReviews(
            @PathVariable
            Long studioId
    );

    /**
     * 스튜디오 리뷰 상세 조회
     */
    @Operation(summary = "스튜디오 리뷰 상세 조회", description = "리뷰의 상세정보를 조회합니다.")
    @GetMapping("/review/{reviewId}/details")
    ResponseEntity<ReviewDetailDto> getReviewDetail(
            @PathVariable
            Long reviewId
    );
}
