package com.example.toucheese_be.domain.studio.service;

import com.example.toucheese_be.domain.studio.dto.StudioDto;
import com.example.toucheese_be.domain.studio.entity.Concept;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.repository.ConceptRepository;
import com.example.toucheese_be.domain.studio.repository.StudioRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudioService {
    private final StudioRepository studioRepository;
    private final ConceptRepository conceptRepository;

    // TODO: 컨셉 조회


    // TODO: 컨셉에 따른 스튜디오 목록 조회
    public List<StudioDto> getStudiosByConcept(Long conceptId) {
        // conceptId 로 Concept 조회
        Concept concept = conceptRepository.findById(conceptId)
                .orElseThrow(() -> new IllegalArgumentException("해당 컨셉이 존재하지 않습니다."));
        // concept에 해당하는 studio들 조회
        List<Studio> studios = studioRepository.findByConcept(concept);

        return studios.stream()
                .map(StudioDto::fromEntity)
                .collect(Collectors.toList());
    }


    // TODO: 컨셉에 따른 스튜디오 목록 조건 추가 검색
}
