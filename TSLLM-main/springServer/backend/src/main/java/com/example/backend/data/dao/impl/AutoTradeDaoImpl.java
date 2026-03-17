package com.example.backend.data.dao.impl;

import com.example.backend.data.dao.AutoTradeDao;
import com.example.backend.data.entity.trading.AutoTrade;
import com.example.backend.data.repository.AutoTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutoTradeDaoImpl implements AutoTradeDao {
    private final AutoTradeRepository autoTradeRepository;
    @Autowired
    public AutoTradeDaoImpl(AutoTradeRepository autoTradeRepository) {
        this.autoTradeRepository = autoTradeRepository;
    }

    @Override
    public List<AutoTrade> findUserId(Long userid, String state) {
        return autoTradeRepository.findByUserId_IdAndState(userid,state);
    }
}
