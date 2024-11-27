package com.example.toucheese_be.domain.review.entity;

import com.example.toucheese_be.domain.item.entity.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String imageUrl;

    @Column(length = 150)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewImageEntity> reviewImage = new ArrayList<>();
}