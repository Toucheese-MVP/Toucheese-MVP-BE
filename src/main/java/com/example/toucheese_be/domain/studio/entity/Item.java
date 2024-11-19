package com.example.toucheese_be.domain.studio.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 상품 이름
    private String name;
    // 상품 설명
    private String description;
    // 상품 가격
    private Integer price;
    // 상품 이미지
    private String image;
    // 스튜디오
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;
}
