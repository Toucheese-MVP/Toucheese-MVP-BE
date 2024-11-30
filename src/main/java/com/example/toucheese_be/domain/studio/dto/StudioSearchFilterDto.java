package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.constant.Popularity;
import com.example.toucheese_be.domain.studio.entity.constant.PriceFilter;
import com.example.toucheese_be.domain.studio.entity.constant.Region;
import java.util.List;
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
    private List<Region> region;
    private Popularity popularity;
    private PriceFilter priceFilter;

    @Builder.Default
    private int page = 0;
    @Builder.Default
    private int size = 10;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
