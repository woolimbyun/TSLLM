package com.example.backend.data.dao.impl;

import com.example.backend.data.dao.RefreshDao;
import com.example.backend.data.entity.user.RefreshEntity;
import com.example.backend.data.repository.RefreshEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshDaoImpl implements RefreshDao {
    private final RefreshEntityRepository refreshEntityRepository;

    @Autowired
    public RefreshDaoImpl(RefreshEntityRepository refreshEntityRepository) {
        this.refreshEntityRepository = refreshEntityRepository;

    }

    @Override
    public Boolean existsByRefresh(String refresh) {
        return refreshEntityRepository.existsById(refresh);
    }

    @Override
    public void deleteByRefresh(String refresh) {
        refreshEntityRepository.deleteById(refresh);
    }

    @Override
    public RefreshEntity save(String userId, String refreshToken) {
        RefreshEntity refresh = new RefreshEntity();

        refresh.setRefreshToken(refreshToken);
        refresh.setUserId(userId);

        refreshEntityRepository.save(refresh);

        return refresh;
    }
}
