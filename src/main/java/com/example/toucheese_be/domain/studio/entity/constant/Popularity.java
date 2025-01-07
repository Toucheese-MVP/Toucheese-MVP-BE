package com.example.toucheese_be.domain.studio.entity.constant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// 인기순 필터
@Schema(description = "인기순 필터")
@Getter
public enum Popularity {
    @Schema(description = "전체")
    ALL(0.0),
    @Schema(description = "3.0 이상")
    ABOVE_3(3.0),
    @Schema(description = "4.0 이상")
    ABOVE_4(4.0),
    @Schema(description = "4.5 이상")
    ABOVE_4_5(4.5);

    private final Double minRating;

    Popularity(Double minRating) {
        this.minRating = minRating;
    }
}
