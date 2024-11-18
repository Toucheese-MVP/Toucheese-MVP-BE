package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.Concept;
import com.example.toucheese_be.domain.studio.entity.Portfolio;
import com.example.toucheese_be.domain.studio.entity.Studio;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchStudioDto {
    private Long id;
    private String name;
    private String profileImg;
    private Double avgAsterion;
    private List<Portfolio> portfolios;

    public static SearchStudioDto fromEntity(Studio entity) {
        return SearchStudioDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .profileImg(entity.getProfileImg())
                .avgAsterion(entity.getAvgAsterion())
                .portfolios(entity.getPortfolios())
                .build();
    }
}
