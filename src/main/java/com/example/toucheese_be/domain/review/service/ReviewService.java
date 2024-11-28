package com.example.toucheese_be.domain.review.service;

import com.example.toucheese_be.domain.review.dto.ReviewDetailDto;
import com.example.toucheese_be.domain.review.dto.ReviewDto;
import com.example.toucheese_be.domain.review.entity.Review;
import com.example.toucheese_be.domain.review.repository.ReviewRepository;
import com.example.toucheese_be.domain.studio.dto.StudioInfoDto;
import com.example.toucheese_be.domain.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public ReviewDto ReviewById(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
        return ReviewDto.fromEntity(review);
    }

    public ReviewDetailDto getReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));

        ReviewDetailDto reviewDetailDto = new ReviewDetailDto();
        ReviewDto reviewDto = ReviewDto.fromEntity(review);
        reviewDetailDto.setReviewDto(reviewDto);

        // 유저 프로필 이미지, 유저 이름
        // reviewDetailDto.setUserProfileDto();

        return reviewDetailDto;
    }
}
