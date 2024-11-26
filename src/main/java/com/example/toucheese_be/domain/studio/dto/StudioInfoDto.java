package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.Portfolio;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.entity.StudioImage;
import com.example.toucheese_be.domain.studio.entity.constant.DutyType;
import com.example.toucheese_be.domain.studio.entity.constant.StudioImageType;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudioInfoDto {
    private Long studioId;
    private String studioName;
    private String studioProfile;
    private List<String> studioBackgrounds;
    private Double popularity;
    private String dutyDate;
    private String address;
    private String studioDescription;

    public static StudioInfoDto fromEntity(Studio entity) {
        // PROFILE 타입 이미지 필터링
        String studioProfile = entity.getImages().stream()
                .filter(image -> image.getType() == StudioImageType.PROFILE)
                .map(StudioImage::getImageUrl)
                .findFirst()
                .orElse(null);

        // BACKGROUND 타입 이미지 필터링
        List<String> studioBackgrounds = entity.getImages().stream()
                .filter(image -> image.getType() == StudioImageType.BACKGROUND)
                .sorted(Comparator.comparingInt(StudioImage::getPosition))
                .map(StudioImage::getImageUrl)
                .toList();

        // TODO: 근무 시간에 대한 반환 문자열 처리

        return StudioInfoDto.builder()
                .studioId(entity.getId())
                .studioName(entity.getName())
                .studioProfile(studioProfile)
                .studioBackgrounds(studioBackgrounds)
                .popularity(entity.getPopularity())
                .dutyDate(null)
                .address(entity.getAddress())
                .studioDescription(entity.getDescription())
                .build();
    }
}
