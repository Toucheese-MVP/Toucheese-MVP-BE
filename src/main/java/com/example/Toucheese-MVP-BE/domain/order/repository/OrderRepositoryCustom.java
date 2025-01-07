package com.example.toucheese_be.domain.order.repository;

import com.example.toucheese_be.domain.user.dto.response.AdminOrderDto;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryCustom {
    Page<AdminOrderDto> findAllOrdersWithDetails(PageRequestDto dto);
}
