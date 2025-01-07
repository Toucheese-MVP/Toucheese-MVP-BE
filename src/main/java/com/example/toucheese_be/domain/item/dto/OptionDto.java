package com.example.toucheese_be.domain.item.dto;

import com.example.toucheese_be.domain.item.entity.Option;
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
public class OptionDto {
    private Long optionId;
    private String optionName;
    private String optionDescription;
    private Integer optionPrice;

    public static OptionDto fromEntity(Option entity) {
        return OptionDto.builder()
                .optionId(entity.getId())
                .optionName(entity.getName())
                .optionDescription(entity.getDescription())
                .optionPrice(entity.getPrice())
                .build();
    }
}
