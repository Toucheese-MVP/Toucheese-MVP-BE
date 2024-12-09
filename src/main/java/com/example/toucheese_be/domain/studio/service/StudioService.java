package com.example.toucheese_be.domain.studio.service;

import com.example.toucheese_be.domain.item.dto.ItemDto;
import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.constant.ItemCategory;
import com.example.toucheese_be.domain.item.repository.ItemRepository;
import com.example.toucheese_be.domain.studio.dto.ConceptDto;
import com.example.toucheese_be.domain.studio.dto.StudioDetailDto;
import com.example.toucheese_be.domain.studio.dto.StudioDto;
import com.example.toucheese_be.domain.studio.dto.StudioInfoDto;
import com.example.toucheese_be.domain.studio.dto.StudioSearchFilterDto;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.repository.ConceptRepository;
import com.example.toucheese_be.domain.studio.repository.StudioRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudioService {
    private final StudioRepository studioRepository;
    private final ConceptRepository conceptRepository;
    private final ItemRepository itemRepository;

    /**
     * 컨셉 조회
     */
    public List<ConceptDto> getConcepts() {
        return conceptRepository.findAll().stream()
                .map(ConceptDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 컨셉에 따른 스튜디오 목록 조건 추가 검색
     */
    public Page<StudioDto> getStudiosByConceptFilters(Long conceptId, StudioSearchFilterDto dto, Pageable pageable) {
        return studioRepository.getStudioListWithPages(conceptId, dto, pageable);
    }


    //public Studio findByName(String studioName) {
    //    return studioRepository.findByName(studioName);
    //}
}
