package com.example.toucheese_be.domain.studio.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    @Column(length=150)
    private Long id;

    //스튜디오 이름
    @Column(length=150)
    private String name;

    // 스튜디오 프로필 사진
    @Column(length=150)
    private String profileImg;

    // 스튜디오에서 예시로 제공하는 대표사진
    @Column(length=150)
    private String portfolioImg;

    // 스튜디오별 평균 별점
    // asterion = 별점
    @Column(length=150)
    private Double avgAsterion;

    // 실제 스튜디오 위치 주소
    @Column(length=150)
    private String address;

    // private int price;
}
