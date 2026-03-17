package com.example.backend.data.entity.user;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

@RedisHash(value = "refreshToken", timeToLive = 86400)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshEntity {


    private String userId;

    @Id
    private String refreshToken;
}
