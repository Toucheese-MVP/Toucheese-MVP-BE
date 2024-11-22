package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.constant.Popularity;
import com.example.toucheese_be.domain.studio.entity.constant.PriceFilter;
import com.example.toucheese_be.domain.studio.entity.constant.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudioSearchFilterDto {
    // 지역 필터
    private Region region;
    // 인기 필터
    private Popularity popularity;
    // 가격 필터
    private PriceFilter priceFilter;
    // 페이징 필터
    @Builder.Default
    private int page = 0;
    @Builder.Default
    private int size = 10;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
