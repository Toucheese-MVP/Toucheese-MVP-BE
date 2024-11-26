package com.example.toucheese_be.domain.studio.entity;


import com.example.toucheese_be.domain.item.entity.Item;
import jakarta.persistence.*;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Studio {
    // 컨셉 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 스튜디오 이름
    @Column(length=150)
    private String name;

    // 스튜디오별 평점
    @Column(length=150)
    private Double popularity;

    // 실제 스튜디오 위치 주소
    @Column(length=150)
    private String address;

    // 스튜디오 공지사항
    @Column(columnDefinition = "TEXT")
    private String description;

    // 컨셉 (여러 스튜디오가 하나의 컨셉에 연결)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id", nullable = false)
    private Concept concept;

    // 스튜디오 이미지
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)
    private List<StudioImage> images = new ArrayList<>();

    // 스튜디오에서 예시로 제공하는 대표사진
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)
    private List<Portfolio> portfolios = new ArrayList<>();

    // 스튜디오 아이템
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)
    private final List<Item> items = new ArrayList<>();

    // 스튜디오의 근무 시간 및 휴무일
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudioDutyDate> studioDutyDates = new ArrayList<>();
}

