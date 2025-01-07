package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.studio.entity.Concept;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConceptDto {
    private Long id;
    private String name;
    private String conceptImg;

    public static ConceptDto fromEntity(Concept entity) {
        return ConceptDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .conceptImg(entity.getConceptImg())
                .build();
    }
}
