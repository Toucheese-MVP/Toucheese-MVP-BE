package com.example.toucheese_be.domain.studio.controller;

import com.example.toucheese_be.domain.studio.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/api/v1/studio")
@RequiredArgsConstructor
public class StudioController {
    private final StudioService studioService;

    // TODO : 컨셉 조회

    // TODO : 스튜디오 조건 검색
}
