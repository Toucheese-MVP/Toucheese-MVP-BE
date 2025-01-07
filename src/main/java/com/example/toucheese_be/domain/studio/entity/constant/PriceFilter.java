package com.example.toucheese_be.domain.studio.entity.constant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "가격 필터")
@Getter
public enum PriceFilter {
    @Schema(description = "10만원 이하")
    BELOW_10(0, 99999),
    @Schema(description = "10만원 이상 20만원 이하")
    BELOW_20(100000, 199999),
    @Schema(description = "20만원 이상")
    ABOVE_20(200000, 1000000);

    private final Integer minPrice;
    private final Integer maxPrice;

    PriceFilter(Integer minPrice, Integer maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
