package com.example.toucheese_be.domain.studio.entity.constant;

import lombok.Getter;

// 인기순 필터
@Getter
public enum Popularity {
    ALL(0.0),
    ABOVE_3(3.0),
    ABOVE_4(4.0),
    ABOVE_4_5(4.5);

    private final Double minRating;

    Popularity(Double minRating) {
        this.minRating = minRating;
    }
}
