package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.entity.StudioDutyDate;
import com.example.toucheese_be.domain.studio.entity.StudioImage;
import com.example.toucheese_be.domain.studio.entity.constant.StudioImageType;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

        // 근무 시간별 그룹화
        Map<String, List<StudioDutyDate>> dutyDateGroups = entity.getStudioDutyDates().stream()
                .collect(Collectors.groupingBy(duty -> String.format("%s-%s", duty.getOpenTime(), duty.getCloseTime())));

        // 휴무일 처리
        String holidays = entity.getStudioDutyDates().stream()
                .filter(StudioDutyDate::isHoliday)
                .map(duty -> convertDayOfWeekToKor(duty.getDayOfWeek().name()))
                .sorted(Comparator.comparingInt(StudioInfoDto::dayOfWeekOrder))
                .collect(Collectors.joining(", "));
        String holidayText = holidays.isEmpty() ? "" : String.format(" / 휴무일: %s", holidays);

        // dutyDate 생성
        String dutyDate = dutyDateGroups.entrySet().stream()
                .map(entry -> {
                    // 시간대
                    String timeRange = entry.getKey();
                    // 해당 시간대의 요일을 정렬하여 문자열로 변환
                    String days = entry.getValue().stream()
                            .map(duty -> duty.getDayOfWeek().name())
                            .sorted(Comparator.comparingInt(day -> dayOfWeekOrder(day)))
                            .map(StudioInfoDto::convertDayOfWeekToKor)
                            .collect(Collectors.joining(", "));
                    // 휴게 시간
                    String breakTime = entry.getValue().stream()
                            .filter(duty -> duty.getBreakStartTime() != null && duty.getBreakEndTime() != null)
                            .findFirst()
                            .map(duty -> String.format(" (휴게 시간: %s-%s)", duty.getBreakStartTime(), duty.getBreakEndTime()))
                            .orElse("");

                    // 최종 조합
                    return String.format("%s: %s%s", days, timeRange, breakTime);
                })
                .collect(Collectors.joining(", ")) + holidayText;



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

    
    // 요일 순서 정렬을 위한 헬퍼 메서드
    private static int dayOfWeekOrder(String dayOfWeek) {
        return switch (dayOfWeek) {
            case "MONDAY" -> 1;
            case "TUESDAY" -> 2;
            case "WEDNESDAY" -> 3;
            case "THURSDAY" -> 4;
            case "FRIDAY" -> 5;
            case "SATURDAY" -> 6;
            case "SUNDAY" -> 7;
            default -> 8;
        };
    }

    // 영어 요일명을 한글 요일명으로 변환하는 헬퍼 메서드
    private static String convertDayOfWeekToKor(String dayOfWeek) {
        return switch (dayOfWeek) {
            case "MONDAY" -> "월";
            case "TUESDAY" -> "화";
            case "WEDNESDAY" -> "수";
            case "THURSDAY" -> "목";
            case "FRIDAY" -> "금";
            case "SATURDAY" -> "토";
            case "SUNDAY" -> "일";
            default -> "";
        };
    }
}
