package com.example.toucheese_be.domain.studio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudioSearchRequestDto {
    private StudioSearchFilterDto filters;
    private PageRequestDto pageable;
}
