package com.example.toucheese_be.domain.review.repository;

import com.example.toucheese_be.domain.review.entity.Review;
import com.example.toucheese_be.domain.studio.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
}
