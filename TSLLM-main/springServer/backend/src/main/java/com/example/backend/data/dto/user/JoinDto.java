package com.example.backend.data.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {
    private String userId;
    private String password;
    private String accessKey;
    private String secretKey;
}
