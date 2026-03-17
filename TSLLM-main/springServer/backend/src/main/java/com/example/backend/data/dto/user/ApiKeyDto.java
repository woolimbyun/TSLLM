package com.example.backend.data.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiKeyDto {
    private Long id;
    private String accessKey;

    private String secretKey;
}
