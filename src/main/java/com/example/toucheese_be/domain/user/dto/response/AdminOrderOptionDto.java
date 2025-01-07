package com.example.toucheese_be.domain.user.dto.response;

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
public class AdminOrderOptionDto {
    private Long optionId;
    private String optionName;
    private Integer optionPrice;
    private Integer optionQuantity;
}
