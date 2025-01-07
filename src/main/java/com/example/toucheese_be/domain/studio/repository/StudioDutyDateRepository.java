package com.example.toucheese_be.domain.studio.repository;

import com.example.toucheese_be.domain.studio.entity.StudioDutyDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioDutyDateRepository extends JpaRepository<StudioDutyDate, Long> {
    List<StudioDutyDate> findStudioDutyDatesByStudioId(Long studioId);
}
