package com.example.toucheese_be.domain.studio.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Concept {
    VIBRANT("생동감있는"),     // 생동감 있는, 활기차고 에너지가 넘치는 느낌
    FLASHY_GLOSSY("플래시/유광"),  // 빛나는 효과, 화려한 디자인
    VIVID("선명한"),           // 색상이 선명하고 뚜렷한 느낌
    WATERCOLOUR("수체화그림체"); // 수채화 스타일의 부드럽고 아련한 느낌

    private final String description;
}
