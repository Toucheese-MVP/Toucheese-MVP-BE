package com.example.toucheese_be.domain.review.service;

import com.example.toucheese_be.domain.review.dto.ReviewDetailDto;
import com.example.toucheese_be.domain.review.dto.ReviewDto;
import com.example.toucheese_be.domain.review.entity.Review;
import com.example.toucheese_be.domain.review.repository.ReviewRepository;
import com.example.toucheese_be.domain.studio.dto.StudioInfoDto;
import com.example.toucheese_be.domain.user.dto.UserProfileDto;
import com.example.toucheese_be.domain.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewDto ReviewById(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
        return ReviewDto.fromEntity(review);
    }

    public ReviewDetailDto getReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
        ReviewDto reviewDto = ReviewDto.fromEntity(review);

        // 유저 프로필 이미지, 유저 이름
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setName(review.getUser().getName());
        userProfileDto.setProfileImg(review.getUser().getProfile_img());

        ReviewDetailDto reviewDetailDto = new ReviewDetailDto();
        reviewDetailDto.setReviewDto(reviewDto);
        reviewDetailDto.setUserProfileDto(userProfileDto);


        return reviewDetailDto;
    }
}
