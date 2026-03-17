package com.example.backend.data.dao.impl;

import com.example.backend.data.dao.OrderDao;
import com.example.backend.data.entity.trading.Order;
import com.example.backend.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;
    @Autowired
    public OrderDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findOrder(Long userId) {
        return orderRepository.findByAutoTradeId_UserId_Id(userId);
    }

    @Override
    public List<Order> findCompleteOrder(Long userId,String state){
        return orderRepository.findByAutoTradeId_UserId_IdAndState(userId, state);
    }
}
