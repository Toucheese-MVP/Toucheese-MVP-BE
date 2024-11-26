package com.example.toucheese_be.domain.studio.entity;

import com.example.toucheese_be.domain.studio.entity.constant.DayType;
import com.example.toucheese_be.domain.studio.entity.constant.DutyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudioDutyDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id", nullable = false)
    private Studio studio;

    // 근무 시간 또는 휴무일 여부
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DutyType type;

    // 근무 시간의 요일 (주 단위)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayType day;

    // 근무 시작 시간 (타입: time)
    private LocalTime openTime;

    // 근무 종료 시간 (타입: time)
    private LocalTime closeTime;
}
