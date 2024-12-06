package com.example.toucheese_be.domain.order.repository;

import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryCustom {
    List<AdminOrderDto> findAllOrdersWithDetails();
}
