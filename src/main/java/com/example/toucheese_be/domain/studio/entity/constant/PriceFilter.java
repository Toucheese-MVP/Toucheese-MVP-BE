package com.example.toucheese_be.domain.studio.entity.constant;

import lombok.Getter;

@Getter
public enum PriceFilter {
    BELOW_10(0, 99999),
    BELOW_20(100000, 199999),
    ABOVE_20(200000, 1000000);

    private final Integer minPrice;
    private final Integer maxPrice;

    PriceFilter(Integer minPrice, Integer maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
