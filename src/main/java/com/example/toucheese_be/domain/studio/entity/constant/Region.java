package com.example.toucheese_be.domain.studio.entity.constant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// 지역순 필터
@Schema(description = "지역 필터")
@Getter
public enum Region {
    @Schema(description = "전체")
    ALL("전체"),
    @Schema(description = "강남구")
    GANGNAM("강남구"),
    @Schema(description = "강서구")
    GANGSEO("강서구"),
    @Schema(description = "서초구")
    SEOCHO("서초구"),
    @Schema(description = "송파구")
    SONGPA("송파구"),
    @Schema(description = "마포구")
    MAPO("마포구"),
    @Schema(description = "영등포구")
    YEONGDEUNGPO("영등포구"),
    @Schema(description = "강북구")
    GANGBUK("강북구"),
    @Schema(description = "용산구")
    YONGSAN("용산구"),
    @Schema(description = "성동구")
    SEONGDONG("성동구");

    private final String description;

    Region(String description) {
        this.description = description;
    }
}
