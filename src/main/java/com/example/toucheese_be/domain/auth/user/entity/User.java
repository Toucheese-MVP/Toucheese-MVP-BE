package com.example.toucheese_be.domain.auth.user.entity;

import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.review.entity.Review;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String name;

    @Column(length = 150)
    private String profile_img;

    private String phoneNumber;

    @Column(length = 150, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
}
