package com.example.backend.data.dao;

import com.example.backend.data.entity.user.RefreshEntity;

public interface RefreshDao {
    Boolean existsByRefresh(String refresh);

    void deleteByRefresh(String refresh);

    RefreshEntity save(String userId, String refreshToken);
}
