package com.example.toucheese_be.domain.review.service;

import com.example.toucheese_be.domain.review.dto.ReviewDetailDto;
import com.example.toucheese_be.domain.review.dto.ReviewDto;
import com.example.toucheese_be.domain.review.dto.ReviewImageDto;
import com.example.toucheese_be.domain.review.entity.Review;
import com.example.toucheese_be.domain.review.repository.ReviewRepository;
import com.example.toucheese_be.domain.studio.dto.StudioInfoDto;
import com.example.toucheese_be.domain.studio.dto.StudioReviewDetailDto;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.repository.StudioRepository;
import com.example.toucheese_be.domain.auth.user.dto.UserProfileDto;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StudioRepository studioRepository;

    // 스튜디오 상세 페이지 조회 - 리뷰탭
    public ResponseEntity<StudioReviewDetailDto> getStudioReviews(Long studioId) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new EntityNotFoundException("스튜디오를 찾을 수 없습니다."));
        StudioInfoDto studioInfoDto = StudioInfoDto.fromEntity(studio);

        List<ReviewImageDto> reviewImageDtos = studio.getItems().stream()
                .flatMap(item -> ReviewImageDto.fromEntity(item).stream())
                .collect(Collectors.toList());

        return ResponseEntity.ok(StudioReviewDetailDto.builder()
                .studioInfoDto(studioInfoDto)
                .reviewImageDtos(reviewImageDtos)
                .build());
    }

    // 스튜디오 리뷰 상세 조회
    public ResponseEntity<ReviewDetailDto> getReviewDetails(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.REVIEW_NOT_FOUND));
        ReviewDto reviewDto = ReviewDto.fromEntity(review);

        // 유저 프로필 이미지, 유저 이름
        UserProfileDto userProfileDto = UserProfileDto.builder()
                .name(review.getUser().getName())
                .profileImg(review.getUser().getProfile_img())
                .build();

        return ResponseEntity.ok(ReviewDetailDto.builder()
                .reviewDto(reviewDto)
                .userProfileDto(userProfileDto)
                .build());
    }
}
