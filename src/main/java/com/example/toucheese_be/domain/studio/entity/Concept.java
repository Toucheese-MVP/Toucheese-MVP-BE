package com.example.toucheese_be.domain.studio.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
스튜디오 마다 갖고있는 컨셉 필드를 설명할 enum 클래스
 */
@Getter
@RequiredArgsConstructor
public enum Concept {
    VIBRANT("생동감있는"),
    FLASHY_GLOSSY("플래시/유광"),
    VIVID("선명한"),
    WATERCOLOUR("수체화그림체");

    private final String description;
}
