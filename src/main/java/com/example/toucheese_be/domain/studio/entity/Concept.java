package com.example.toucheese_be.domain.studio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 컨셉 이름
    @Column(length = 150, nullable = false)
    private String name;
    private String conceptImg;

    // 컨셉에 연결된 여러 스튜디오
    @OneToMany(mappedBy = "concept", fetch = FetchType.LAZY)
    private List<Studio> studios = new ArrayList<>();
}
