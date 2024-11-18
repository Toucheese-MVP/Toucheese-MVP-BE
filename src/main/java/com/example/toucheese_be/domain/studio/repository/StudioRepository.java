package com.example.toucheese_be.domain.studio.repository;

import com.example.toucheese_be.domain.studio.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudioRepository extends JpaRepository<Studio, Long> {
}
