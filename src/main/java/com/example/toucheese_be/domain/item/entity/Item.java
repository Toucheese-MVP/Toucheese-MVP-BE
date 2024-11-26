package com.example.toucheese_be.domain.item.entity;


import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.item.entity.constant.ItemCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
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
    // 상품 카테고리
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;
    // 상품 가격
    private Integer price;
    // 상품 이미지
    private String image;
    // 스튜디오
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOption> itemOptions = new ArrayList<>();
}
