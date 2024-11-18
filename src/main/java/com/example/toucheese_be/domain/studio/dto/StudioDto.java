package com.example.toucheese_be.domain.studio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudioDto {
    private Long id;
    private String name;
    private String profileImg;
}
