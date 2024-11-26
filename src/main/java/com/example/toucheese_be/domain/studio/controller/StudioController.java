package com.example.toucheese_be.domain.studio.controller;

import com.example.toucheese_be.domain.studio.dto.ConceptDto;
import com.example.toucheese_be.domain.studio.dto.StudioSearchFilterDto;
import com.example.toucheese_be.domain.studio.dto.StudioDto;
import com.example.toucheese_be.domain.studio.service.StudioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studio")
@RequiredArgsConstructor
public class StudioController {
    private final StudioService studioService;

    /**
     * 전체 컨셉 조회
     */
    @GetMapping("/concepts")
    public ResponseEntity<List<ConceptDto>> getConcepts() {
        return ResponseEntity.ok(studioService.getConcepts());
    }


    /**
     * 컨셉 별 스튜디오 조회 (전체 or 조건)
     */
    @PostMapping("/search/{conceptId}")
    public ResponseEntity<Page<StudioDto>> getStudiosByConceptAndFilters(
            @PathVariable
            Long conceptId,
            @RequestBody(required = false)
            StudioSearchFilterDto dto
    ) {
        if (dto == null) {
            dto = StudioSearchFilterDto.builder().build();
        }
        Pageable pageable = dto.toPageable();
        return ResponseEntity.ok(studioService.getStudiosByConceptFilters(conceptId, dto, pageable));
    }

    /**
     * TODO: 스튜디오 상세 조회 - 가격 탭
     */
    @GetMapping("/details/{studioId}/items")
    public void getStudioItems() {}

    /**
     * TODO: 스튜디오 상세 조회 - 리뷰탭
     */
    @GetMapping("/details/{studioId}/reviews")
    public void getStudioReviews() {}

    /**
     * TODO: 스튜디오 근무 시간 조회 - 캘린더 보여주기
     */

}
