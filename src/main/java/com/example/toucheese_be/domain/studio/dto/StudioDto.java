package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.Portfolio;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.entity.StudioImage;
import com.example.toucheese_be.domain.studio.entity.constant.StudioImageType;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudioDto {
    private Long id;
    private String name;
    private String profileImg;
    private Double popularity;
    private List<String> portfolios;

    public static StudioDto fromEntity(Studio entity) {
        // PROFILE 타입 이미지 필터링
        String profileImage = entity.getImages().stream()
                .filter(image -> image.getType() == StudioImageType.PROFILE)
                .map(StudioImage::getImageUrl)
                .findFirst()
                .orElse(null);

        return StudioDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .profileImg(profileImage)
                .popularity(entity.getPopularity())
                .portfolios(entity.getPortfolios().stream()
                        .map(Portfolio::getImageUrl)
                        .collect(Collectors.toList()))
                .build();
    }
}
