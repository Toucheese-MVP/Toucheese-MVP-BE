package com.example.toucheese_be.domain.studio.controller;

import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.service.StudioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studio")
@RequiredArgsConstructor
public class StudioController {
    private final StudioService studioService;

    // TODO : 전체 컨셉 조회
    @GetMapping("/")
    public void getConcepts() {}

    // TODO : 컨셉 별 스튜디오 전체 조회
    @GetMapping("/{conceptId}")
    public void getStudiosByConcept() {};


    // TODO: 컨셉 별 스튜디오 조건 (인기, 가격, 거리) 검색
    @PostMapping("/{conceptId}/search")
    public void getStudiosByConceptAndFilters() {}

}
