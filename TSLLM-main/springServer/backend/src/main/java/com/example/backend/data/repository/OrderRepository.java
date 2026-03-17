package com.example.backend.data.repository;

import com.example.backend.data.entity.trading.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByAutoTradeId_UserId_Id(Long userId);

    List<Order> findByAutoTradeId_UserId_IdAndState(Long userId,String state);
}
