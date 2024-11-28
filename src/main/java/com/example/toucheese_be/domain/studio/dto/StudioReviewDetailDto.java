package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.review.dto.ReviewDto;
import com.example.toucheese_be.domain.review.dto.ReviewImageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudioReviewDetailDto {
    private StudioInfoDto studioInfoDto;
    private List<ReviewImageDto> reviewImageDtos;
}
