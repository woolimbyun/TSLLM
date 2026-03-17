package com.example.backend.data.repository;

import com.example.backend.data.entity.trading.AutoTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AutoTradeRepository extends JpaRepository<AutoTrade,Long> {
    List<AutoTrade> findByUserId_IdAndState(Long id, String state);
}
