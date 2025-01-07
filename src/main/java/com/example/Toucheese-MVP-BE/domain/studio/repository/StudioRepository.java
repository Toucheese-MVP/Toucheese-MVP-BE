package com.example.toucheese_be.domain.studio.repository;

import com.example.toucheese_be.domain.studio.entity.Concept;
import com.example.toucheese_be.domain.studio.entity.Studio;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long>, StudioRepositoryCustom{
    List<Studio> findByConcept(Concept concept);

    Studio findByName(String studioName);
}
