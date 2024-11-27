package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.entity.StudioImage;
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

        // 근무 시간 데이터 조합
        String dutyDate = entity.getStudioDutyDates().stream()
                .map(duty -> {
                    // 휴게 시간
                    String breakTime = (duty.getBreakStartTime() != null && duty.getBreakEndTime() != null)
                            ? String.format(" (휴식시간 %s-%s) ", duty.getBreakStartTime(), duty.getBreakEndTime())
                            : "";
                    // 휴무일 처리
                    String holidays = (duty.getHolidays() != null)
                            ? String.format("/ 매주 %s 휴무", duty.getHolidays())
                            : "";
                    // 일반적인 근무 시간
                    return String.format("근무 시간: %s-%s%s %s", duty.getOpenTime(), duty.getCloseTime(), breakTime, holidays);
                })
                .collect(Collectors.joining(", "));

        return StudioInfoDto.builder()
                .studioId(entity.getId())
                .studioName(entity.getName())
                .studioProfile(studioProfile)
                .studioBackgrounds(studioBackgrounds)
                .popularity(entity.getPopularity())
                .dutyDate(dutyDate)
                .address(entity.getAddress())
                .studioDescription(entity.getDescription())
                .build();
    }
}
