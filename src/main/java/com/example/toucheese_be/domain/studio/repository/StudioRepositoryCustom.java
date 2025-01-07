package com.example.toucheese_be.domain.studio.repository;

import com.example.toucheese_be.domain.studio.dto.StudioDto;
import com.example.toucheese_be.domain.studio.dto.StudioSearchFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepositoryCustom {
    Page<StudioDto> getStudioListWithPages(Long conceptId, StudioSearchFilterDto dto, Pageable pageable);
}
