package com.example.toucheese_be.domain.studio.repository;

import com.example.toucheese_be.domain.studio.entity.Concept;
import com.example.toucheese_be.domain.studio.entity.Studio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudioRepository extends JpaRepository<Studio, Long> {
    List<Studio> findByConcept(Concept concept);
}
