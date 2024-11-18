package com.example.toucheese_be.studio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Setter
    private String imageUrl;
    @Setter
    private String email;
    @Setter
    private String phone;
    @Setter
    private String instaUrl;
    @Setter
    private String region;
    @Setter
    private String address;
    @Setter
    private Integer price;
    @Setter
    private String schedule;
    @Setter
    private String announcement;
    @Setter
    private String notes;
}
