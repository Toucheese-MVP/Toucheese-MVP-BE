package com.example.toucheese_be.domain.order.repository;

import com.example.toucheese_be.domain.order.entity.OrderOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOptionRepository extends JpaRepository<OrderOption, Long> {

}
