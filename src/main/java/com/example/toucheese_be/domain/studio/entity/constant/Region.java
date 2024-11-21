package com.example.toucheese_be.domain.studio.entity.constant;

import lombok.Getter;

// 지역순 필터
@Getter
public enum Region {
    ALL("전체"),
    GANGNAM("강남구"),
    GANGSEO("강서구"),
    SEOCHO("서초구"),
    SONGPA("송파구"),
    MAPO("마포구"),
    YEONGDEUNGPO("영등포구"),
    GANGBUK("강북구"),
    YONGSAN("용산구"),
    SEONGDONG("성동구");

    private final String description;

    Region(String description) {
        this.description = description;
    }
}
