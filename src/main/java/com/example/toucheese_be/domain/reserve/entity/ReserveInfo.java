package com.example.toucheese_be.domain.reserve.entity;

import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.Option;
import com.example.toucheese_be.domain.reserve.entity.constant.NowReserveInfo;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.entity.StudioDutyDate;
import com.example.toucheese_be.domain.studio.entity.constant.StudioImageType;
import com.example.toucheese_be.domain.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ReserveInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "studio_id", nullable = false)
    private Studio studio;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

//    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StudioDutyDate> studioDutyDates = new ArrayList<>();
    private LocalDateTime reserveDateTime;

    @Enumerated(EnumType.STRING)
    private NowReserveInfo nowReserveInfo;
}
