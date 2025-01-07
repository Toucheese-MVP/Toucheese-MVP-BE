package com.example.toucheese_be.domain.studio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudioReviewDetailDto {
    private StudioInfoDto studioInfoDto;
    private List<ReviewImageDto> reviewImageDtos;
}
