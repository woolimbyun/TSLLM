package com.example.backend.data.dao;

import com.example.backend.data.entity.trading.AutoTrade;

import java.util.List;

public interface AutoTradeDao {
    List<AutoTrade> findUserId(Long userid, String state);
}
