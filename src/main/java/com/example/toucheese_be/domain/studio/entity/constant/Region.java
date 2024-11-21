package com.example.toucheese_be.domain.studio.entity.constant;

import lombok.Getter;

// 지역순 필터
@Getter
public enum Region {
    ALL("전체"),
    GANGNAM("강남"),
    SEOCHO("서초"),
    SONGPA("송파"),
    MAPO("마포"),
    YEONGDEUNGPO("영등포"),
    GANGBUK("강북"),
    YONGSAN("용산"),
    SEONGDONG("성동");

    private final String description;

    Region(String description) {
        this.description = description;
    }
}
