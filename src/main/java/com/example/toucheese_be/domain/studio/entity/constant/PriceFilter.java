package com.example.toucheese_be.domain.studio.entity.constant;

import lombok.Getter;

@Getter
public enum PriceFilter {
    ALL(null, null),
    BELOW_10(0, 100000),
    BELOW_20(0, 200000),
    ABOVE_20(200000, null);

    private final Integer minPrice;
    private final Integer maxPrice;

    PriceFilter(Integer minPrice, Integer maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
