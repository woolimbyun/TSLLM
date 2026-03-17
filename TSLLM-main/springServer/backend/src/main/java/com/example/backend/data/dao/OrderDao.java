package com.example.backend.data.dao;

import com.example.backend.data.entity.trading.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findOrder(Long userId);

    List<Order> findCompleteOrder(Long userId, String state);
}
