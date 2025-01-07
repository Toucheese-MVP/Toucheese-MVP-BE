package com.example.toucheese_be.domain.studio.repository;


import com.example.toucheese_be.domain.studio.entity.Concept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptRepository extends JpaRepository<Concept, Long> {
}
