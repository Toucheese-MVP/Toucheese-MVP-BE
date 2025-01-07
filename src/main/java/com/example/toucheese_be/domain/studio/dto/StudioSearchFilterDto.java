package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.constant.Popularity;
import com.example.toucheese_be.domain.studio.entity.constant.PriceFilter;
import com.example.toucheese_be.domain.studio.entity.constant.Region;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @ArraySchema(
            schema = @Schema(description = "지역 필터", implementation = Region.class, nullable = true)
    )
    private List<Region> region;
    @Schema(description = "인기순 필터", implementation = Popularity.class, nullable = true)
    private Popularity popularity;
    @Schema(description = "가격 필터", implementation = PriceFilter.class, nullable = true)
    private PriceFilter priceFilter;

    @Schema(description = "페이지 번호", defaultValue = "0", example = "0")
    @Builder.Default
    private int page = 0;
    @Schema(description = "페이지 사이즈", defaultValue = "10", example = "10")
    @Builder.Default
    private int size = 10;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
