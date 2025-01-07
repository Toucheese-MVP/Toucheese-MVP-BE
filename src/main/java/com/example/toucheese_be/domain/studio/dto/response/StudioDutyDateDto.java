package com.example.toucheese_be.domain.studio.dto.response;

import com.example.toucheese_be.domain.studio.entity.StudioDutyDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
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
public class StudioDutyDateDto {
    private Long id;
    private Integer weekOfMonth;
    private DayOfWeek dayOfWeek;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalTime breakStartTime;
    private LocalTime breakEndTime;
    private boolean isHoliday;

    public static StudioDutyDateDto fromEntity(StudioDutyDate entity) {
        return StudioDutyDateDto.builder()
                .id(entity.getId())
                .weekOfMonth(entity.getWeekOfMonth())
                .dayOfWeek(entity.getDayOfWeek())
                .openTime(entity.getOpenTime())
                .closeTime(entity.getCloseTime())
                .breakStartTime(entity.getBreakStartTime())
                .breakEndTime(entity.getBreakEndTime())
                .isHoliday(entity.isHoliday())
                .build();
    }
}
